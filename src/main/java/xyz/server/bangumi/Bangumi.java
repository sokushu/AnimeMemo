package xyz.server.bangumi;

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

import xyz.bangumi.mysql.dao.AnimeDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Bangumi {
	
	@Autowired
	private AnimeDao anime;
	@Autowired
	private User_AnimeDao useranime;

	/**
	 * 查看动画数据
	 * @param animeid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.GET)
	public String bangumilist(@PathVariable("animeid")String animeid, Model model) {
		
		Map<String, Object> aa = anime.findByAnimeID(animeid);
		/*
		 * 可用的数据：anime类中的字段
		 */
    	model.addAllAttributes(aa);
    	return "bangumi/bangumi";
	}
	
	/*
	 * ajax进行请求
	 */
	/**
	 * 订阅动画
	 * @return
	 */
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.POST)
	@ResponseBody
	public String bangumiSubscriber(@PathVariable("animeid")String animeid, HttpSession session) {
		String uid = session.getAttribute("USERUID").toString();
		Map<String, Object>map = anime.findByAnimeID(animeid);
		String a = map.get("anime_number").toString();
		useranime.add(animeid, uid, a);
		return "true";
	}
}
