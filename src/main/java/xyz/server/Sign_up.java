package xyz.server;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

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
	public Map<String, String> signUpPost(@Valid Users user, BindingResult result, HttpServletResponse res) {
		//进行表单验证
		if (result.hasErrors()) {
			Map<String, String>maperror = new Hashtable<>();
			for(int i = 0; i < result.getAllErrors().size(); i++){
				maperror.put(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			}
			return maperror;
		}else{
			//添加用户注册信息
			users.addUser(user);
			try {
				//进行页面跳转
				res.sendRedirect("/sign_in");
			} catch (IOException e) {}
			return null;
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
