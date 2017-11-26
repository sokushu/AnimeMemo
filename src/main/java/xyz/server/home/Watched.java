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

import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Watched {
	
	@Autowired
	private User_AnimeDao useranime;
	@Autowired
	private UserDao users;
	
	@RequestMapping(value = "/id/{url}/bangumi/watched", method = RequestMethod.GET)
	public String GetWatched(@PathVariable("url")String url, Model model, String page) {
		
		int Page = page(page);
		
		Map<String, Object>map1 = users.showUserByURL(url);
		
		PageHelper.startPage(Page, 20);
		List<Map<String, Object>>map = useranime.findWatched(map1.get("uid").toString());
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(map);
		
		model.addAttribute("data", map);
		model.addAttribute("pagefirst", 1);
		model.addAttribute("pageone", pageinfo.getPrePage());
		model.addAttribute("pagenow", pageinfo.getPageNum());
		model.addAttribute("pagetwo", pageinfo.getNextPage());
		model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
		model.addAttribute("kazu", pageinfo.getTotal());
		model.addAttribute("isnext", pageinfo.isHasNextPage());
		model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		
		return "user/watched";
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
