package xyz.server.home.jishi;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// import xyz.bangumi.mysql.dao.User_articleDao;

/**
 * 得到记事
 * 实现方法，将Html文本保存到硬盘中。
 * 然后将保存路径信息存储到数据库中。
 * 读取时将文本文件渲染到Html中
 */
// @Controller
public class JishiGet {

    // @Autowired
    // private User_articleDao user_articDao;
    /**得到记事 */
    @RequestMapping(value = "id/{url}/jishi/{jishiID}", method = RequestMethod.GET)
    public String get(@PathParam("url") String url,
    @PathParam("jishiID") String jishiID, Model model
    ){
        //从数据库中查找硬盘路径
        // String jishi = user_articDao.loadjishi();
        String jishi = "user_articDao.loadjishi()";
        //判断是否能找到 
        if (jishi == null) {
            //如果内容不存在，提示无此文章
            model.addAttribute("jishi", "未找到这篇文章，请检查地址是否正确");
            //返回一个记事本网页
            return "jishi";
        } else {
            //将内容进行加载
            model.addAttribute("jishi", jishi);
            //返回一个记事本网页
            return "jishi";
        }
    }
}