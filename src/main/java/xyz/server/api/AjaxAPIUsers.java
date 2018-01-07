package xyz.server.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xyz.bangumi.mysql.dao.CommentsDao;
import xyz.bangumi.mysql.dao.UserDao;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/user")
public class AjaxAPIUsers {
	
	@Autowired
	private CommentsDao comm;
	@Autowired
	private UserDao user;
	/**
	 * 获取用户的留言
	 */
	@RequestMapping(value = "/comments/{url}", method = RequestMethod.GET)
	public List<Map<String, Object>> getComm(@PathVariable("url")String url){
		Map<String, Object> map = user.showUserByURL(url);
		String uid = map.get("uid").toString();
		List<Map<String, Object>>commlist = comm.showComm(uid);
		return commlist;
	}

}
