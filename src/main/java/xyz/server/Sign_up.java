package xyz.server;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.bangumi.mysql.bean.Users;
import xyz.bangumi.mysql.dao.UserDao;

@Controller
@EnableAutoConfiguration
public class Sign_up {
	
	@Autowired
	private UserDao users;
	
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	@ResponseBody
	public String signUpPost(@Valid Users user, BindingResult result, HttpServletResponse res) {
		try {
			//人为制造空指针
			String username = users.findUserByUsername(user.getUsername()).get("username").toString();
			String url = users.showUserByURL(user.getUrl()).get("url").toString();
			return "用户名或URL已经被占用，" + "您输入的是" + username + "，和" + url;
		} catch (Exception e) {
			//进行表单验证
			if (result.hasErrors()) {
				//返回一条错误信息
				return result.getFieldError().getDefaultMessage();
			}else{
				//添加用户注册信息
				users.addUser(user);
				//返回验证信息
				return "true";
			}
		}
	}
	/**
	 * 显示注册页面
	 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String signUpGet() {
		return "users/sign_up";
	}
}
