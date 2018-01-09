package xyz.server;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * 搜索，一般也就是搜索动画
 * @author miri
 *
 */
@Controller
@EnableAutoConfiguration
public class Search {
	
	@Autowired
	private AnimeDao anime;
	/**
	 * 进行动画的搜索
	 */
	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public String search(String w, Model model, String page) {
		if (page == null||page.equals("")) {
			int Page = 1;
			se(Page, model, w);
			return "search";
		}
		int Page = StringToInt(page);
		se(Page, model, w);
		return "search";
	}
	/**String 转换 int */
	private int StringToInt(String num){
		try {
			int a = new Integer(num);
			return a;
		} catch (Exception e) {
			//TODO: handle exception
			return 1;
		}
	}
	/**
	 * 进行分页
	 */
	public void se(int Page, Model model, String w) {
		PageHelper.startPage(Page, 9);
		/**对动画进行搜索 */
		List<Map<String, Object>>search = anime.searchAnime(w);
		if (search.size() == 0) {
			/**如果没有搜索结果的情况 */
			model.addAttribute("ishas", "false");
			/**还需要对HTML文件进行进一步的加工 */
		}else{
			/**如果有搜索结果的话 */
			/**对返回的结果进行分页（使用了一个分页插件） */
			PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(search);
			/**用来指示是否有搜索结果 */
			model.addAttribute("ishas", "true");
			model.addAttribute("title", w);
			model.addAttribute("list", search);
			
			//分页信息
			//得到第一页
			model.addAttribute("pagefirst", 1);
			//上一页
			model.addAttribute("pageone", pageinfo.getPrePage());
			//当前页
			model.addAttribute("pagenow", pageinfo.getPageNum());
			//下一页
			model.addAttribute("pagetwo", pageinfo.getNextPage());
			//最后一页
			model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
			model.addAttribute("kazu", pageinfo.getTotal());
			model.addAttribute("isnext", pageinfo.isHasNextPage());
			model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		}
		
	}
}
