package xyz.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.bangumi.mysql.bean.Users;
import xyz.bangumi.mysql.dao.UserDao;

@Controller
@EnableAutoConfiguration
public class Sign_up {
	
	@Autowired
	private UserDao users;
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String signUpPost(Users user) {
		users.addUser(user);
		return "redirect:/sign_in";
	}
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String signUpGet() {
		return "users/sign_up";
	}
}
