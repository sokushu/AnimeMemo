package xyz.server.home;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
 * 用于页面的各类显示，例如登录按钮的显示等
 */
public class ShowHome {
    /**
     * int type: 0 是以登录自己，1 是以登录别人 2是未登录
     */
    public Model showHome(Model model, int type){
        if (type == 0) {
            model.addAttribute("logined", "true");
            model.addAttribute("isme", "true");
        }
        if (type == 1) {
            model.addAttribute("logined", "true");
            model.addAttribute("isme", "false");
        }
        if (type == 2) {
            model.addAttribute("logined", "false");
            model.addAttribute("isme", "false");
        }
        return model;
    }

    /**
     * 判断是否显示登录注册等按钮
     */
    public Model showLogin(Model model, HttpSession session){
        try {
            //获取session中的用户信息
            String UID = session.getAttribute("USERUID").toString();
            String URL = session.getAttribute("USERURL").toString();
            //用户已经登录的情况
            model.addAttribute("logined", "true");
            return model;
        } catch (Exception e) {
            //如果出错就是没登录
            model.addAttribute("logined", "false");
            return model;
        }
    }
}