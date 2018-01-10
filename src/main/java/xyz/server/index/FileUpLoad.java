package xyz.server.index;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.MainRun;
import xyz.bangumi.mysql.dao.AnimeDao;
import xyz.bangumi.mysql.dao.UserDao;

/**
 * 文件上传
 * @author miri
 *
 */
@Controller
public class FileUpLoad {
	
	@Autowired
	private AnimeDao anime;
	@Autowired
	private UserDao user;
	
     /**
      *  
      * @param request
      * @param response
      * @param p
      * @return
      * @throws ServletException
      * @throws IOException
      */
    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, 
    		@RequestHeader HttpHeaders headers, HttpSession session)  
            throws ServletException, IOException {
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
			anime.updataPic(data, animeid);
		}
    	if (getpath.equals("/id/")) {
    		String uid = session.getAttribute("USERUID").toString();
			user.updataPic(data, uid);
		}
    }
}
