package xyz.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.server.home.Test;

@Controller
@EnableAutoConfiguration
public class Sign_in{
	
	@Autowired
	private UserDao user;

	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public void sign_in(String username, String password, HttpSession session, HttpServletResponse response) throws IOException {
		try {
			/*
			 * 读取用户信息
			 */
			Map<String, Object> readUser = user.findUserByUsername(username);
			if (readUser!=null) {
				if (readUser.get("password").equals(password)) {
					session.setAttribute("USERURL", readUser.get("url")); 
					session.setAttribute("USERUID", readUser.get("uid"));
					session.setAttribute("USERNAME", readUser.get("username"));
					
//					return "redirect:/id/" + readUser.get("url");
					// 重定向到个人主页
					response.sendRedirect("/id/"+ readUser.get("url"));
					return;
				}
				response.sendRedirect("/sign_in");
			}
			response.sendRedirect("/sign_in");
		} catch (Exception e) {
			response.sendRedirect("/sign_in");
		}
		
	}
	
	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String sign_in_get(Model model) {
		return "users/sign_in";
	}
}
