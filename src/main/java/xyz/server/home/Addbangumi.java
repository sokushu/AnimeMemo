package xyz.server.home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * 添加动画数据
 */
@Controller
@EnableAutoConfiguration
public class Addbangumi {
	
	@Autowired
	private AnimeDao animedao;
	
	/**对动画进行添加操作 */
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.POST)
	public String addbangumiPost(@PathVariable("username")String username, Anime anime, BindingResult result, 
	HttpServletResponse res) {
		if (result.hasErrors()) {
			/**
			 * 拿到错误信息
			 */
			Map<String, String> maperr = new HashMap<>();
			result.getFieldErrors();
			maperr.put(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			/**返回错误信息 */
			return "maperr";
		} else {
			/**正确的情况下，写入数据库 */
			animedao.insert(anime);
			/**
			 * 跳转回添加页面
			 */
			return "redirect:/id/" + username + "/addbangumi";
		}
	}
	
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.GET)
	public String addbangumiGet(@PathVariable("username")String username, Model model) {
		/*
		 * 用来显示页面
		 */
		model.addAttribute("url", username);
		return "user/addbangumi";
	}
}
