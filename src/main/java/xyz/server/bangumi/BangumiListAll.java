package xyz.server.bangumi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * BangumiListAll
 */
@Controller
@EnableAutoConfiguration
public class BangumiListAll {

    @Autowired
	private AnimeDao anime;

    /**
	 * 这里显示所有的动画
	 * @return
	 */
	@RequestMapping(value = "/bangumi/list", method = RequestMethod.GET)
	public String list(Model model, String page) {
		
		int Page = page(page);
		
		PageHelper.startPage(Page, 20);
		List<Map<String, Object>>list = anime.returnAllAnime();
		PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(list);
		
		model.addAttribute("data", list);
		
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
		//
		model.addAttribute("isnext", pageinfo.isHasNextPage());
		model.addAttribute("ispre", pageinfo.isHasPreviousPage());
		
		return "/bangumi/bangumilist";
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