package xyz.server.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import xyz.InfoData;
import xyz.bangumi.core.home.HomeCore;
import xyz.bangumi.mysql.dao.CommentsDao;
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Home extends HomeCore {
     
	@Autowired
	private UserDao user;
	@Autowired
	private SELECT select;
	@Autowired
	private User_AnimeDao useranime;
	@Autowired
	private CommentsDao comm;
	
	/**用于显示用户的个人主页 */
    @RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
    public String homeid(@PathVariable("url") String url, HttpSession session, Model model) {
		
		if (haveErrStr(url)) {
			//有非法字符
			return HomeError;
		}
		
		return homm(url, session, model, Method_ID);

	}
	@RequestMapping(value = "/home/{uid}", method = RequestMethod.GET)
	public String home(@PathVariable("uid")String uid, HttpSession session, Model model){

		if (isNumber(uid) == false) {
			//不是数字ID
			return HomeError;
		}

		return homm(uid, session, model, Method_Home);
	}
	private Map<String, Object> getMap(String type, String uid, String url){
		if (type.equals(Method_Home)) {
			return user.showUser(uid);
		}else if (type.equals(Method_ID)) {
			return user.showUserByURL(url);
		}
		return null;
	}
	private String homm(String url, HttpSession session, Model model, String method){

		Map<String, Object>map = getMap(method, url, url);
		if (map == null || map.size() == 0) {
			//没有这个用户
			return HomeError;
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
		
		//显示留言

		// List<Map<String, Object>>commlist = comm.showComm(uid);
		// if (commlist == null || commlist.size() == 0) {
		// 	model.addAttribute("commlist", "null");
		// }else{
		// 	model.addAttribute("commlist", commlist);
		// }

		//对登录状态和非登录状态显示的东西进行调整
		model = super.Home(session, model, url, uid);

    	return HomePage;
	}
	
    /**
	 * http://localhost/id/
	 * 可以返回个人home页（如果登录过了）
	 * 或是首页
	 */
    @RequestMapping(value = {"/id", "/home"}, method = RequestMethod.GET)
    public String homeget(HttpSession session) {
		try {
			String METHOD = session.getAttribute(InfoData.Session_USERMETHOD).toString();
			if (METHOD.equals("URL")) {
				/**得到用户的URL，如果未登录则出错 */
				String URL = session.getAttribute(InfoData.Session_USERURL).toString();
				/**跳转到用户个人页面 */
				return "redirect:/id/" + URL;
			}else if (METHOD.equals("ID")) {
				String UID = session.getAttribute(InfoData.Session_USERUID).toString();
				return "redirect:/home/" + UID;
			}else{
				return "redirect:/";
			}
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
}

