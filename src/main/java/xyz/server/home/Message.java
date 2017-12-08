package xyz.server.home;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Message {

    /**
     * 用来显示页面用
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String Showmessage(Model model, HttpSession session){
        return "";
    }

    /**
     * 发送消息
     */
    public String send(){
        return "";
    }
}
