package moe.neptunenoire.phantom.index;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;

import moe.InfoData;
import moe.MainRun;
import moe.neptunenoire.IndexServer;
import moe.neptunenoire.mysql.bean.Users;
import moe.neptunenoire.mysql.dao.MaiKissReo;
import moe.neptunenoire.mysql.dao.MySQL;
import moe.neptunenoire.util.StringCheck;

/**
 * Index
 */
public class Index extends StringCheck {

    // 数据库操作
    @Autowired
    private MySQL mysql;

    /**
     * 数据库的相关操作
     */
    @Autowired
    private MaiKissReo maireo;

    /** 首页动画的缓存 */
    private List<Map<String, Object>> animeList = null;
    /** 
     * 判断用户是否已经登陆
     * @param session SESSION
     */
    protected boolean is_SignIn(HttpSession session){
        if (session.getAttribute(InfoData.Session_USERUID)!=null) {
            return true;
        }
        return false;
    }

    /** 
     * 显示首页的最新动画
     * @return 返回首页最新添加的8部动画
     */
    protected List<Map<String, Object>> index_Anime(){
        // 如果不存在
        if (animeList == null) {
            animeList = maireo.Anime_FindIndexAnime(8);
        }
        return animeList == null ? null : animeList;
    }

    /**
     * ==============================================================
     * 用户注册
     * ==============================================================
     */
    /**
     * 用户的注册
     * @param users
     */
    protected boolean signUp(Users users){
        try {
            maireo.User_AddUser(users);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 用户注册失败后的提示信息
     * @param model
     * @param returnUrl
     */
    protected String signNo(Model model, String returnUrl){
        model.addAttribute("haserror", true);
        model.addAttribute("error", "注册失败");
        return returnUrl;
    }
    /**
     * ==============================================================
     * 用户注册  结束
     * ==============================================================
     */
    /**
     * ==============================================================
     * 登陆
     * ==============================================================
     */
    protected int sign_inimpl(String username, String password, HttpSession session, HttpHeaders headers, Model model){ 
        try {
            Map<String, Object> userInfo = maireo.User_FindUserByUsername(username);
            // 找到用户信息
            if (isNull(userInfo) == false) {
                // 找到用户名 验证密码
                if (((String)(userInfo.get("password"))).equals(password)) {
                    session.setAttribute(InfoData.Session_USERNAME, userInfo.get("username"));
                    session.setAttribute(InfoData.Session_UserPageName, userInfo.get("pageusername"));
                    session.setAttribute(InfoData.Session_UserBickPic, userInfo.get("backpic"));
                    session.setAttribute(InfoData.Session_UserEmail, userInfo.get("email"));
                    session.setAttribute(InfoData.Session_UserInfo, userInfo.get("info"));
                    session.setAttribute(InfoData.Session_UserPic, userInfo.get("userpic"));
                    session.setAttribute(InfoData.Session_USERUID, userInfo.get("uid"));
                    // session.setAttribute(InfoData.Session_USERYURI, userInfo.get("username"));
                    if (isNull((String)(userInfo.get("url")))) {
                        session.setAttribute(InfoData.Session_USERURL, userInfo.get("uid"));
                        session.setAttribute(InfoData.Session_USERMETHOD, InfoData.MethodHome);
                    }else{
                        session.setAttribute(InfoData.Session_USERURL, userInfo.get("url"));
                        session.setAttribute(InfoData.Session_USERMETHOD, InfoData.MethodID);
                    }
                    // 登陆成功
                    return 200;
                }else{
                    // 用户名密码错误，返回登陆页面
                    return 404;
                }
            }else{
                // 用户名密码错误，返回登陆页面
                return 404;
            }
        } catch (Exception e) {
            // 未知错误 ，返回登陆页面
            return 403;
        }
    }
    Map<Integer, String> codeInfo = new LinkedHashMap<Integer, String>(){
        private static final long serialVersionUID = 1L;
		{
            put(404, "用户名或密码，请重新输入");
            put(403, "未知原因，登陆失败");
        }
    };
    protected String getCodeinfo(int code){
        return codeInfo.get(code);
    }
    Map<Integer, String> codeurl = new LinkedHashMap<Integer, String>(){
        private static final long serialVersionUID = 1L;
		{
            put(404, "redirect:" + IndexServer.sign_in);
            put(403, "redirect:" + IndexServer.sign_in);
        }
    };
    protected String code(int code, HttpSession session){
        if (code != 200) {
            return codeurl.get(code);
        }
        // 200 的情况，不可能出现空的情况
        // 如果出现，就是未知错误
        String method = session.getAttribute(InfoData.Session_USERMETHOD).toString();
        String url = session.getAttribute(InfoData.Session_USERURL).toString();
        if (method.equals(InfoData.MethodHome)) {
            return "redirect:/home/" + url;
        }
        if (method.equals(InfoData.MethodID)) {
            return "redirect:/id/" + url;
        }
        return "redirect:/";
    }
    /**
     * ==============================================================
     * 登陆 结束
     * ==============================================================
     */
    /**
     * ==============================================================
     * 文件上传
     * ==============================================================
     */
    /**
    *  文件上传
    * @param request
    * @param response
    * @param p
    * @return
    * @throws ServletException
    * @throws IOException
    */
    protected String uploadImpl(HttpServletRequest request, HttpServletResponse response, 
            HttpHeaders headers, HttpSession session){
        String from = headers.get("Referer").toString();
        //文件保存的目录
        String rootpath = MainRun.filePath;
        /*
        * 判断请求地址，返回不同路径
        */
        String getPath = getSavePath(from);
        /*
        * 如果是非法请求 ban掉
        */
        if (getPath==null) {
            return "false";
        }
        
        String savePath = rootpath + getPath;
        // 如果文件存放路径不存在，则创建一个  
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {  
            fileSaveDir.mkdirs();  
        }
        
        try {
        for (Part part : request.getParts()) {
            String t_ext = extractFileName(part).substring(extractFileName(part).lastIndexOf(".") + 1);
            long filename = System.currentTimeMillis();
            part.write(savePath + "img" + filename +"."+t_ext);  
            /*
            * 将上传的图片的路径信息保存到数据库中
            */
            String savedataURL = "/img" + getPath + "img" +filename +"."+t_ext;
            getdatasave(getPath, savedataURL, session, from);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ServletException e) {
        e.printStackTrace();
    }
        return "true";
    }

    private String extractFileName(Part part) {  
        String contentDisp = part.getHeader("content-disposition");  
        String[] items = contentDisp.split(";");  
        for (String s : items) {  
            if (s.trim().startsWith("filename")) {  
                return s.substring(s.indexOf("=") + 2, s.length()-1);  
            }  
        }  
        return "";  
    }
    
    /**
     * 判断是从那里来的请求
     * @param from
     * @return
     */
    private String getSavePath(String from) {
        //浏览器URL路径
        if (from.indexOf("/bangumi/")>-1) {
            //返回实际硬盘路径
            return "/bangumi/";
        }
        if (from.indexOf("/id/")>-1) {
            return "/user/";
        }
        return null;
    }
    /**拿到数据的 动画ID 或 用户ID */
    private void getdatasave(String getpath, String data, HttpSession session, String from) {
        if (getpath.equals("/bangumi/")) {
            String animeid = from.substring(from.indexOf("bangumi/") + 8).substring(0, from.substring(from.indexOf("bangumi/") + 8).indexOf("/"));
            mysql.Anime_UpDataPic(data, animeid);
        }
        if (getpath.equals("/id/")) {
            String uid = session.getAttribute("USERUID").toString();
            mysql.User_UpdataPic(data, uid);
        }
    }
    /**
     * ==============================================================
     * 文件上传 结束
     * ==============================================================
     */
    
}
