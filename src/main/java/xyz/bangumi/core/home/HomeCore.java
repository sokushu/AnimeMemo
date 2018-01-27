package xyz.bangumi.core.home;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import xyz.bangumi.tools.StringCheck;

/**
 * HomeCore
 */
public class HomeCore extends StringCheck {

    protected String Method_ID = "id";
    protected String Method_Home = "home";

    protected String HomeError = "";
    protected String HomePage = "user/home";

    protected Model Home(HttpSession session, Model model, String url, String uid){
        try {
    		String UID = session.getAttribute("USERUID").toString();
    		String URL = session.getAttribute("USERURL").toString();
    		
    		if (URL.equals(url) && UID.equals(uid)) {
    			/*
    			 * 以登录，访问自己的界面
    			 */ 
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "true");
				return model;
			}else {
				/*
				 * 以登录，访问他人页面
				 */
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "false");
				return model;
			}	
		} catch (NullPointerException e) {
			/*
			 * 未登录
			 */
			model.addAttribute("logined", "false");
            model.addAttribute("isme", "false");
			return model;
		}
    }
    @Override
    public boolean isNumber(String num) {
        return super.isNumber(num);
    }
    @Override
    public boolean haveErrStr(String string) {
        return super.haveErrStr(string);
    }
}