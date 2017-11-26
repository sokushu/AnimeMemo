package xyz.server.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.bangumi.mysql.dao.UserDao;

@RestController
@RequestMapping("/ajaxs")
public class Ajax {
	
	@Autowired
	private UserDao user;
	/*
	 * 登录验证
	 */
	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public boolean ajax_login(String username, String password) {
		Map<String, Object>map = user.findUserByUsername(username);
		String user = map.get("username").toString();
		String pass = map.get("password").toString();
		if (user.equals(username)&&pass.equals(password)) {
			return true;
		}
		return false;
	}
	
	/*
	 * 注册验证
	 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String ajax_sign_up() {
		
		return "false";
	}
}
