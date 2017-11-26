package xyz.server.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;

@Controller
@EnableAutoConfiguration
public class Addbangumi {
	
	@Autowired
	private AnimeDao animedao;
	
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.POST)
	public String addbangumiPost(@PathVariable("username")String username, Anime anime) {
		animedao.insert(anime);
		/*
		 * 添加结束，返回，或者继续添加
		 */
		return "redirect:/id/" + username + "/addbangumi";
	}
	
	@RequestMapping(value = "/id/{username}/addbangumi", method = RequestMethod.GET)
	public String addbangumiGet(@PathVariable("username")String username) {
		/*
		 * 用来显示页面
		 */
		return "user/addbangumi";
	}
}
