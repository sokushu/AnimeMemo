package moe.neptunenoire.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.InfoData;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.UtilsPack;


@Controller
@EnableAutoConfiguration
public class Home extends UtilsPack {

	@Autowired
	private MaiKissReo mysql;

	protected String Method_ID = "id";
    protected String Method_Home = "home";

    protected String HomeError = "";
    protected String HomePage = "user/home";

    @Override
    public boolean isNumber(String num) {
        return super.isNumber(num);
    }

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
			return null;//mysql.User_FindUser(uid);
		}else if (type.equals(Method_ID)) {
			return null;//mysql.User_FindUserByURL(url);
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

    	List<Map<String, Object>> watching = null;//mysql.findWatchingAnimeInfoBy3(uid);
    	List<Map<String, Object>> watched = null;//mysql.findWatchedAnimeInfoBy3(uid);

		int watchingKazu = 0;//mysql.UserWatchingAnime(uid);
		int watchedKazu = 0;//mysql.UserWatchedAnime(uid);

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
		model = Home2(session, model, url, uid);

    	return HomePage;
	}
	private Model Home2(HttpSession session, Model model, String url, String uid){
        try {
    		String UID = session.getAttribute("USERUID").toString();
    		String URL = session.getAttribute("USERURL").toString();

    		if (URL.equals(url) && UID.equals(uid)) {
    			/*
    			 * 以登录，访问自己的界面
    			 */
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "true");
				return model;
			}else {
				/*
				 * 以登录，访问他人页面
				 */
				model.addAttribute("logined", "true");
            	model.addAttribute("isme", "false");
				return model;
			}
		} catch (NullPointerException e) {
			/*
			 * 未登录
			 */
			model.addAttribute("logined", "false");
            model.addAttribute("isme", "false");
			return model;
		}
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
    	Map<String, Object>map = null;//mysql.User_FindUserByURL(url);
    	return map.get("backpic").toString();
	}

	@RequestMapping(value = "/id/{url}/bangumi/list", method = RequestMethod.GET)
	public String bangumilist(@PathVariable("url")String url, Model model, String page) {

		Map<String, Object>userinfo = null;//mysql.User_FindUserByURL(url);
		String uid = userinfo.get("uid").toString();

		List<Map<String,Object>>lista = null;//mysql.findWatchAnime(uid);

		int watchingKazu = 0;//mysql.UserWatchingAnime(uid);
		int watchedKazu = 0;//mysql.UserWatchedAnime(uid);

		int watchKazu = watchedKazu + watchingKazu;
		model.addAttribute("watchingKazu", watchKazu);
		model.addAttribute("url", url);
		model.addAttribute("watching", lista);
		return "user/bangumilist";
	}

	/**已经看过得动画 */
	@RequestMapping(value = "/id/{url}/bangumi/watched", method = RequestMethod.GET)
	public String GetWatched(@PathVariable("url")String url, Model model, String page) {

		Map<String,Object>map = null;//mysql.User_FindUserByURL(url);
		String uid = map.get("uid").toString();
		List<Map<String, Object>>watching = null;//mysql.findWatchedAnimeInfo(uid);

		int watchedKazu = 0;//mysql.UserWatchedAnime(uid);
		{
			{
				model.addAttribute("watchedKazu", watchedKazu);
				model.addAttribute("watched", watching);
				model.addAttribute("url", url);
			}
		}
		return "user/watched";
	}

	/**正在看的动画 */
	@RequestMapping(value = "/id/{url}/bangumi/watching", method = RequestMethod.GET)
	public String GetWatching(@PathVariable("url")String url, Model model) {

		Map<String,Object>map = null;//mysql.User_FindUserByURL(url);
		String uid = map.get("uid").toString();
		List<Map<String, Object>>watching = null;//mysql.findWatchingAnimeInfo(uid);

		int watchingKazu = 0;//mysql.UserWatchingAnime(uid);
		model.addAttribute("watchingKazu", watchingKazu);
		model.addAttribute("watching", watching);
		model.addAttribute("url", url);
		return "user/watching";
	}
}

