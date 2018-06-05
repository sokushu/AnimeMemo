package moe.neptunenoire.web.phantom;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import moe.neptunenoire.InfoData;
import moe.neptunenoire.MainRun;
import moe.neptunenoire.web.controller.IndexServer;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Anime;
import moe.neptunenoire.web.table.Users;
import moe.neptunenoire.web.util.MD5Coding;
import moe.neptunenoire.web.util.StringCheck;
import moe.neptunenoire.web.util.UserID;

/**
 * Index
 */
public class Index extends StringCheck {

    // 数据库操作
	@Autowired
    private MaiKissReo mysql;

    /**
     * 数据库的相关操作
     */
	@Autowired
    private MaiKissReo maireo;

    /** 首页动画的缓存 */
    private List<Map<String, Object>> animeList = null;

    /**
     * ==============================================================
     * 首页
     * ==============================================================
     */

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
     * 首页结束
     * ==============================================================
     */
    /**
     * ==============================================================
     * 用户注册
     * ==============================================================
     */
    /**
     *
     * @param user
     * @return
     */
    protected Map<String, Object> signUpPost(Users user) {

        Map<String, Object> map = new HashMap<>();
        boolean hasError = false;

		try {

			Map<String, Object> thisusername = null;//DataSet.getUser(var->var.get("username").equals(user.getUsername()));

			Map<String, Object> thisurl = null;//DataSet.getUser(var->var.get("url").equals(user.getUrl()));

			String errorUserName = null;
			String errorUrl = null;

			if (isNull(thisusername) == true) {
				thisusername = maireo.User_FindUserByUsername(user.getUsername());
				if (isNull(thisusername)) {
					errorUserName = null;
				}
			}

			if (isNull(thisurl) == true) {
				thisurl = maireo.User_FindUserByShowByURL(user.getUrl());
			}

			//人为制造空指针
			errorUrl = thisurl.get("url").toString();

			hasError = true;
			map.put("hasError", hasError);
			map.put("usernameerror", errorUserName != null ? "用户名已经被占用" : null);
			map.put("urlerror", errorUrl != null ? "url已经被占用" : null);
            //返回一个错误信息
            return map;
		} catch (Exception e) {
            //我这里的想法是，使用正则表达式，对录入的数据进行检查

            String email = user.getEmail();
            String username = user.getUsername();
            String url = user.getUrl();

            String email_RegEx = "^[a-zA-Z_]+[0-9]+@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";
            String username_RegEx = "^[A-Za-z][A-Za-z1-9_-]+$";
            String url_RegEx = "^[a-z]";

            Matcher emailMatcher = Pattern.compile(email_RegEx).matcher(email);
            Matcher usernameMatcher = Pattern.compile(username_RegEx).matcher(username);
            Matcher urlMatcher = Pattern.compile(url_RegEx).matcher(url);

            if (!emailMatcher.matches()) {
                //返回错误信息
                map.put("emailerror", "邮箱不正确");
                hasError = true;
            }
            if (!usernameMatcher.matches()) {
                //返回错误信息
                map.put("usernameerror", "用户名格式不正确");
                hasError = true;
            }
            if (!urlMatcher.matches()) {
                //返回错误信息
            	map.put("urlerror", "url格式不正确");
            	hasError = true;
            }

            //生成UID
            UserID userid = new UserID(username);
            user.setUid(userid.GetlongCode());

            user.setPassword(MD5Coding.coding(user.getUsername(), user.getPassword(), user.getUid()));

            //如果全部通过，则进行注册
            maireo.User_AddUser(user);

            map.put("hasError", hasError);
			return map;
		}
    }

    /**
     * 用户注册失败后的提示信息
     * @param model
     * @param returnUrl
     */
    protected String signNo(int errorCode){
        Map<Integer, String> errorinfo = new HashMap<Integer, String>(){
            private static final long serialVersionUID = 1L;
			{
                put(100, "error");
                put(101, "error");
                put(102, "error");
                put(200, "error");
                put(201, "error");
            }
        };
        return errorinfo.get(errorCode);
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
    /**
     *
     * @param username
     * @param password
     * @param session
     * @param headers
     * @param model
     * @return
     */
    protected int sign_inimpl(String username, String password, HttpSession session, HttpHeaders headers, Model model){
        try {
            Map<String, Object> userInfo = maireo.User_FindUserByID(new UserID(username).toString());
            // 找到用户信息
            if (isNull(userInfo) == false) {
                // 找到用户名 验证密码
                if (((String)(userInfo.get("password"))).equals(MD5Coding.coding(username, password, (long)(userInfo.get("password"))))) {
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

    /**
     * 相应的代码，返回相应的错误信息
     */
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
    /**
     * 相应的代码，返回相应的页面
     */
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
     * 使用Ajax进行登陆
     */
    protected boolean Sign_inajaxImpl(String username, String password){
        Map<String, Object>readuser = mysql.User_FindUserByUsername(username);
		if (isNull(readuser) == false) {
			String Mapusername = readuser.get("username").toString();
			String Mappassword = readuser.get("password").toString();
			if (Mapusername.equals(username) && Mappassword.equals(password)) {
				//验证成功
				return true;
			}else{
                return false;
            }
		}else{
            return false;
        }
    }
    /**
     * ==============================================================
     * 登陆 结束
     * ==============================================================
     */
    /**
     * ==============================================================
     * 登陆用户退出
     * ==============================================================
     */
    protected void signOut(HttpSession session){
        if (session.getAttribute(InfoData.Session_USERUID) != null) {
            session.invalidate();
        }
    }
    /**
     * ==============================================================
     * 登陆用户退出 结束
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
    /**
     * ==============================================================
     * 添加番组数据
     * ==============================================================
     */
    protected String addbangumiPostImpl(Anime anime){
    	/**
    	 * 对数据进行检查
    	 */
    	String animeName = anime.getAnime_name();
        return "";
    }
    /**
     * ==============================================================
     * 添加番组数据 结束
     * ==============================================================
     */
    /**
     * ==============================================================
     * 搜索
     * ==============================================================
     */
    protected String searchImpl(String w, Model model, String page) {
		/**搜索各种信息，动画，用户等 */
        int Page = StringToNum(page);

        PageHelper.startPage(Page, 9);
		/**对动画进行搜索 */
		List<Map<String, Object>>search = mysql.Anime_SearchAnime(w);
		if (search.size() == 0) {
			/**如果没有搜索结果的情况 */
			model.addAttribute("ishas", "false");
			/**还需要对HTML文件进行进一步的加工 */
		}else{
			/**如果有搜索结果的话 */
			/**对返回的结果进行分页（使用了一个分页插件） */
			PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(search);
			/**用来指示是否有搜索结果 */
			model.addAttribute("ishas", "true");
			model.addAttribute("title", w);
			model.addAttribute("list", search);

			//分页信息
			//得到第一页
			model.addAttribute("pagefirst", 1);
			//上一页
			model.addAttribute("pageone", pageinfo.getPrePage());
			//当前页
			model.addAttribute("pagenow", pageinfo.getPageNum());
			//下一页
			model.addAttribute("pagetwo", pageinfo.getNextPage());
			//最后一页
			model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
			model.addAttribute("kazu", pageinfo.getTotal());
			model.addAttribute("isnext", pageinfo.isHasNextPage());
			model.addAttribute("ispre", pageinfo.isHasPreviousPage());
        }

		return "search";
    }
    /**
     * ==============================================================
     * 搜索 结束
     * ==============================================================
     */
}
