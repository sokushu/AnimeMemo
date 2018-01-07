package xyz.server.home.jishi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.MainRun;
// import xyz.bangumi.mysql.dao.User_articleDao; 

/**
 * 写一些文章之类的东西用的
 */
// @Controller
// @EnableAutoConfiguration
//记事
public class Jishi {

    // @Autowired
    // private User_articleDao user_artDao;
    /**
     * 添加记事
     */
    @RequestMapping(value = "id/{url}/jishi", method = RequestMethod.POST)
    public String add(@PathParam("url") String url, String jishiString){
        try {
            //创建URL文件夹
            String path = newfile(url);
            //将获取到的html存储到硬盘中
            writetxt(jishiString, path);
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到Jishi.Java writetxt");
        } catch (IOException e) {
            System.out.println("创建文件夹出错Jishi.Java newfile");
        } 
        finally {
            //写入数据库
            // user_artDao.addjishi();
            //返回保存成功页面
            return "";
        }
    }
    private String newfile(String url)throws IOException{
        String path = MainRun.filePath1 + "jishi/" + url + "/";
        File filename = new File(path);
        if (!filename.exists()) {
            filename.mkdirs();
            //文件夹子创建成功
            return path;
        }
        //已经有这个路径了
        return path;
    }
    private boolean writetxt(String jishiString, String path)throws FileNotFoundException{
        //创建文件
        File file = new File(path);
        //写入文件
        FileOutputStream fo = new FileOutputStream(path);
        return false;
    }
}
