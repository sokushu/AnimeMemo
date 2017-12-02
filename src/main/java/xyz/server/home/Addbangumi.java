package xyz.server.home;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addbangumiPost(@PathVariable("username")String username, @Valid Anime anime, BindingResult result, 
	HttpServletResponse res) {
		if (result.hasErrors()) {
			Map<String, String> maperr = new HashMap<>();
			maperr.put(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			return maperr;
		} else {
			animedao.insert(anime);
			/**
			 * 跳转回添加页面
			 */
			try {
				res.sendRedirect("/id/" + username + "/addbangumi");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.GET)
	public String addbangumiGet(@PathVariable("username")String username) {
		/*
		 * 用来显示页面
		 */
		return "user/addbangumi";
	}
}
