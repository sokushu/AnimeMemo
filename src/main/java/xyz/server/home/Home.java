package xyz.server.home;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	/*
     * 
     */
    @RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
    public String homeid(@PathVariable("url") String url, HttpSession session, Model model) {
    	Map<String, Object>map = user.showUserByURL(url);
    	if (map == null) {
    		return "redirect:/id";
		}
    	String uid = map.get("uid").toString();
    	List<Map<String, Object>> watching = select.findWatchingAnimeInfo(uid);
//    	List<Map<String, Object>> watched = select.findWatchedAnimeInfo(map.get("uid").toString());
    	
    	int watchingKazu = useranime.UserWatchingAnime(uid);
    	
//    	if (watching == null) {
    		model.addAttribute("showwatch", "true");
    		model.addAttribute("watchingKazu", watchingKazu);
    		model.addAttribute("watching", watching);
//        	model.addAttribute("watched", watched);
//		}else {
//			model.addAttribute("showwatch", "false");
			model.addAttribute("watch", "空空的 >.< 该用户还没有订阅信息");
//		}
    	
    	model.addAttribute("userinfo", map);
    	
    	
    	
    	try {
    		String UID = session.getAttribute("USERUID").toString();
    		String URL = session.getAttribute("USERURL").toString();
    		
    		if (URL.equals(url) && UID.equals(map.get("uid").toString())) {
    			/*
    			 * 以登录，访问自己的界面
    			 */
    			model.addAttribute("button_edit", "true");
    			model.addAttribute("button_message", "false");
				return "user/home";
			}else {
				/*
				 * 以登录，访问他人页面
				 */
				model.addAttribute("button_edit", "false");
    			model.addAttribute("button_message", "true");
    			//显示留言
				return "user/home";
			}	
		} catch (NullPointerException e) {
			/*
			 * 未登录
			 */
			model.addAttribute("button_edit", "false");
			model.addAttribute("button_message", "false");
			model.addAttribute("login", "false");
			return "user/home";
		}
    }
    
    @RequestMapping(value = "/id/{url}", method = RequestMethod.POST)
    public void homepost(@PathVariable("url") String url, HttpSession session, Model model) {
    	homeid(url, session, model);
    }
    
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public String homeget(HttpSession session) {
    	try {
    		String URL = session.getAttribute("USERURL").toString();
    		return "redirect:/id/" + URL;
		} catch (Exception e) {
			// TODO: handle exception
			return "redirect:/index.html";
		}
    }
    
    @RequestMapping(value = "/id/getbackimg", method = RequestMethod.GET)
    @ResponseBody
    public String getpic(String url) {
    	Map<String, Object>map = user.showUserByURL(url);
    	return map.get("back_pic").toString();
    }
}

