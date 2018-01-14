package xyz.server.bangumi;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * 对动画数据的编辑
 */
@Controller
@EnableAutoConfiguration
public class BangumiEdit {

    @Autowired
	private AnimeDao anime;
    /**
	 * 对动画进行修改操作
	 */
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.POST)
	public String edit(@PathVariable("animeid")String animeid, Anime anime0) {
		anime.updata(animeid, anime0);
		return "redirect:/bangumi/" + animeid;
	}
	/**
	 * 得到动画的修改页面
	 */
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.GET)
	public String bangumiedit(@PathVariable("animeid")String animeid, Model model) {
		Map<String, Object> map = anime.findByAnimeID(animeid);
		model.addAllAttributes(map);
		return "/bangumi/bangumiedit";
    }
    
}