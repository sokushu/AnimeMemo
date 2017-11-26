package xyz.server.home.firends;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirendsHome {

	@RequestMapping(value = "/id/{username}/home", method = RequestMethod.GET)
	public String home(@PathVariable("username")String username) {
		
		return "";
	}
	
	/**
	 * ajax的方式
	 * 添加评论，文章之类
	 * comm的形式是html文本的形式
	 * @return
	 */
	@RequestMapping(value = "/id/{username}/home", method = RequestMethod.POST)
	public String comm(@PathVariable("username")String username, String comm) {
		
		return "";
	}
}
