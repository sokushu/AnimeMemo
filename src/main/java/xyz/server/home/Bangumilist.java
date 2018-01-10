package xyz.server.home;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;
/**
 * 显示动画现在看的，
 * 看完的，全部的动画列表
 */
@Controller
@EnableAutoConfiguration
public class Bangumilist {
	
	@Autowired
	private SELECT select;
	@Autowired
	private UserDao user;
	@Autowired
	private User_AnimeDao useranime;

	@RequestMapping(value = "/id/{url}/bangumi/list", method = RequestMethod.GET)
	public String bangumilist(@PathVariable("url")String url, Model model, String page) {
		
		// int Page = page(page);
		
		// Map<String, Object>map = user.showUserByURL(url);
		
		// PageHelper.startPage(Page, 20);
		// List<Map<String, Object>> bangumilist = useranime.selectAll(map.get("uid").toString());
		// PageInfo<Map<String, Object>>pageinfo = new PageInfo<>(bangumilist);
		
		// model.addAttribute("data", bangumilist);
		// model.addAttribute("pagefirst", 1);
		// model.addAttribute("pageone", pageinfo.getPrePage());
		// model.addAttribute("pagenow", pageinfo.getPageNum());
		// model.addAttribute("pagetwo", pageinfo.getNextPage());
		// model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
		// model.addAttribute("kazu", pageinfo.getTotal());
		// model.addAttribute("isnext", pageinfo.isHasNextPage());
		// model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		Map<String, Object>userinfo = user.showUserByURL(url);
		String uid = userinfo.get("uid").toString();

		List<Map<String,Object>>lista = select.findWatchAnime(uid);

		int watchingKazu = useranime.UserWatchingAnime(uid);
		int watchedKazu = useranime.UserWatchedAnime(uid);

		int watchKazu = watchedKazu + watchingKazu;
		model.addAttribute("watchingKazu", watchKazu);
		model.addAttribute("url", url);
		model.addAttribute("watching", lista);
		return "user/bangumilist";
	}
	
	/**已经看过得动画 */
	@RequestMapping(value = "/id/{url}/bangumi/watched", method = RequestMethod.GET)
	public String GetWatched(@PathVariable("url")String url, Model model, String page) {
		
		// int Page = page(page);
		
		// Map<String, Object>map1 = users.showUserByURL(url);
		
		// PageHelper.startPage(Page, 20);
		// List<Map<String, Object>>map = useranime.findWatched(map1.get("uid").toString());
		// PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(map);
		
		// model.addAttribute("data", map);
		// model.addAttribute("pagefirst", 1);
		// model.addAttribute("pageone", pageinfo.getPrePage());
		// model.addAttribute("pagenow", pageinfo.getPageNum());
		// model.addAttribute("pagetwo", pageinfo.getNextPage());
		// model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
		// model.addAttribute("kazu", pageinfo.getTotal());
		// model.addAttribute("isnext", pageinfo.isHasNextPage());
		// model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		Map<String,Object>map = user.showUserByURL(url);
		String uid = map.get("uid").toString();
		List<Map<String, Object>>watching = select.findWatchedAnimeInfo(uid);

		int watchedKazu = useranime.UserWatchedAnime(uid);
		model.addAttribute("watchedKazu", watchedKazu);
		model.addAttribute("watched", watching);
		model.addAttribute("url", url);
		return "user/watched";
	}

	/**正在看的动画 */
	@RequestMapping(value = "/id/{url}/bangumi/watching", method = RequestMethod.GET)
	public String GetWatching(@PathVariable("url")String url, Model model) {
		
		// int Page = page(page);
		
		// Map<String, Object>map = user.showUserByURL(url);
		
		// PageHelper.startPage(Page, 20);
		// List<Map<String, Object>>map0 = useranime.findWatching(map.get("uid").toString());
		// PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(map0);
		
		// model.addAttribute("data", map0);
		
		// model.addAttribute("pagefirst", 1);
		// model.addAttribute("pageone", pageinfo.getPrePage());
		// model.addAttribute("pagenow", pageinfo.getPageNum());
		// model.addAttribute("pagetwo", pageinfo.getNextPage());
		// model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
		// model.addAttribute("kazu", pageinfo.getTotal());
		// model.addAttribute("isnext", pageinfo.isHasNextPage());
		// model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		Map<String,Object>map = user.showUserByURL(url);
		String uid = map.get("uid").toString();
		List<Map<String, Object>>watching = select.findWatchingAnimeInfo(uid);

		int watchingKazu = useranime.UserWatchingAnime(uid);
		model.addAttribute("watchingKazu", watchingKazu);
		model.addAttribute("watching", watching);
		model.addAttribute("url", url);
		return "user/watching";
	}

	private int page(String page) {
		try {
			Integer a = new Integer(page);
			return a;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
}
