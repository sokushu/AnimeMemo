package xyz.server.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.bangumi.mysql.bean.Users;
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Home {
    
	@Autowired
	private UserDao user;
	@Autowired
	private SELECT select;
	@Autowired
	private User_AnimeDao useranime;
	
	/**用于显示用户的个人主页 */
    @RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
    public String homeid(@PathVariable("url") String url, HttpSession session, Model model) {
    	Map<String, Object>map = user.showUserByURL(url);
		//判断是否存在该用户
		if (map == null) {
    		return "redirect:/id";
		}
    	String uid = map.get("uid").toString();
    	List<Map<String, Object>> watching = select.findWatchingAnimeInfoBy3(uid);
    	List<Map<String, Object>> watched = select.findWatchedAnimeInfoBy3(uid);
    	
		int watchingKazu = useranime.UserWatchingAnime(uid);
		int watchedKazu = useranime.UserWatchedAnime(uid);
    	//对home页面进行控制
    	if (watchingKazu != 0) {
    		model.addAttribute("showwatching", "true");
			model.addAttribute("watchingKazu", watchingKazu);
			model.addAttribute("watching", watching);
		}else {
			model.addAttribute("showwatching", "false");
			model.addAttribute("watchingKazu", 0);
		}
		if (watchedKazu != 0) {
			model.addAttribute("showwatched", "true");
			model.addAttribute("watchedKazu", watchedKazu);
    		model.addAttribute("watched", watched);
		} else {
			model.addAttribute("showwatched", "false");
			model.addAttribute("watchedKazu", 0);
		}
		if (watchingKazu + watchedKazu == 0) {
			model.addAttribute("watchinfo", "空空的 >.< 该用户还没有订阅信息");
		}
    	model.addAttribute("userinfo", map);
		
		ShowHome sss = new ShowHome();
    	try {
    		String UID = session.getAttribute("USERUID").toString();
    		String URL = session.getAttribute("USERURL").toString();
    		
    		if (URL.equals(url) && UID.equals(map.get("uid").toString())) {
    			/*
    			 * 以登录，访问自己的界面
    			 */ 
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "true");
				return "user/home";
			}else {
				/*
				 * 以登录，访问他人页面
				 */
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "false");
				return "user/home";
			}	
		} catch (NullPointerException e) {
			/*
			 * 未登录
			 */
			model.addAttribute("logined", "false");
            model.addAttribute("isme", "false");
			return "user/home";
		}
    }
	
	/**用户登录用 */
    @RequestMapping(value = "/id/{url}", method = RequestMethod.POST)
    public void homepost(@PathVariable("url") String url, HttpSession session, Model model) {
    	homeid(url, session, model);
    }
    /**
	 * http://localhost/id/
	 * 可以返回个人home页（如果登录过了）
	 * 或是首页
	 */
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public String homeget(HttpSession session) {
    	try {
			/**得到用户的URL，如果未登录则出错 */
			String URL = session.getAttribute("USERURL").toString();
			/**跳转到用户个人页面 */
    		return "redirect:/id/" + URL;
		} catch (Exception e) {
			/**如果出错，则是未登录的情况，返回登录页 */
			return "redirect:/sign_in";
		}
    }
    /**获取用户页的背景图片 */
    @RequestMapping(value = "/id/getbackimg", method = RequestMethod.GET)
    @ResponseBody
    public String getpic(String url) {
    	Map<String, Object>map = user.showUserByURL(url);
    	return map.get("backpic").toString();
	}
	
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
}

