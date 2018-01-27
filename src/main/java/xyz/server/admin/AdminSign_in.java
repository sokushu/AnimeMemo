package xyz.server.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 管理员用户登录
 */
@Controller
@EnableAutoConfiguration
public class AdminSign_in {

    /**禁止登录名单 */
    private List<String> list = new ArrayList<>();
    /**管理员用户名 */
    private String adminName = "Nekomimi";
    /**管理员密码 */
    private String adminPassword = "guk8ne";
    private int i = 0;
    @RequestMapping(value = "/admin/sign_in", method = RequestMethod.POST)
    public String Sign_in(String username, String password, HttpSession session){
        /**获取当前用户session id */
        String sessionid = session.getId();
        /**验证请求是否在禁止登录名单 */
        if (list.size() != 0) {
            for (String var : list) {
                if (var.equals(sessionid)) {
                    /**返回禁止登录页面 */
                    return "adminsign_inerror";
                }
            }
        }
        if (adminName.equals(username) && adminPassword.equals(password)) {
            /**添加session信息 */
            session.setAttribute("adminName", adminName);
            session.setAttribute("adminPassword", adminPassword);
            return "redirect:/admin/home";
        } else {
            /**如果密码出错，则添加到禁止登录名单，一段时间不能登录 */
            list.add(i, sessionid);
            i++;
            return "adminsign_inerror";
        }
    }
    @RequestMapping(value = "/admin/sign_in", method = RequestMethod.GET)
    public String getSign_in(){
        return "adminsign_in";
    }

}