package xyz.server.home;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.bangumi.mysql.bean.Users;
import xyz.bangumi.mysql.dao.UserDao;

/**
 * HomeEdit
 */
public class HomeEdit {

    @Autowired
	private UserDao user;
    /*
	 * 修改个人主页
	 */
	@RequestMapping(value = "/id/{url}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("url")String url, Model model) {
		Map<String, Object>map = user.showUserByURL(url);
		model.addAttribute(map);
		return "/user/edit";
	}
	
	/*
	 * 使用ajax进行提交
	 */
	@RequestMapping(value = "/id/{url}/edit", method = RequestMethod.POST)
	public String edit1(@PathVariable("url")String url, Users users, HttpSession session) {
		String uid = session.getAttribute("USERUID").toString();
		Map<String, Object>map = user.showUserByURL(url);
		if (map.get("uid").equals(uid)) {
			user.updataUser(users, uid);
			return "true";
		}
		return "false";
	}
	@RequestMapping(value = "/home/{url}/edit", method = RequestMethod.GET)
	public String edit2(@PathVariable("url")String url, Model model){
		return "";
	}
	@RequestMapping(value = "/home/{url}/edit", method = RequestMethod.POST)
	public String edit3(@PathVariable("url")String url, Users users, HttpSession session){
		return "";
	}
}