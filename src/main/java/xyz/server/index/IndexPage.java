package xyz.server.index;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * IndexPage
 */
@Controller
public class IndexPage {

    /**
     * 用于显示首页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        try {
            /**将会改改首页 */
            session.getAttribute("USERURL");
            model.addAttribute("issign_in", "true");
            return "indexhomepage";
        } catch (Exception e) {
            return "indexhomepage";
        }
        
    }
}