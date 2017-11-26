package xyz.server.bangumi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;

@Controller
public class BangumiEdit {
	
	@Autowired
	private AnimeDao animedao;
	
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.POST)
	public String edit(@PathVariable("animeid")String animeid, Anime anime) {
		animedao.updata(animeid, anime);
		return "redirect:/bangumi/{" + animeid + "}";
	}
	
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.GET)
	public String bangumiedit(@PathVariable("animeid")String animeid, Model model) {
		Map<String, Object> map = animedao.findByAnimeID(animeid);
		model.addAllAttributes(map);
		return "/bangumi/bangumiedit";
	}
}
