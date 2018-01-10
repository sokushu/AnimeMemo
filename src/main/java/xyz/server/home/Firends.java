package xyz.server.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Firends {
	
	@RequestMapping(value = "/id/{username}/firends", method = RequestMethod.GET)
	public String firends(@PathVariable("username")String username) {
		
		return "";
	}
	
	/**
	 * 进行管理操作
	 * @param username
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/id/{username}/firends", method = RequestMethod.POST)
	public String firendsPost(@PathVariable("username")String username, String uid) {
		
		return "";
	}

}
