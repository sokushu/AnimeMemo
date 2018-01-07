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
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Bangumi {
	
	@Autowired
	private AnimeDao anime;
	@Autowired
	private User_AnimeDao useranime;
	@Autowired
	private SELECT select;

	/**
	 * 查看动画数据
	 * @param animeid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.GET)
	public String bangumilist(@PathVariable("animeid")String animeid, Model model, HttpSession session) {
		
		Map<String, Object> aa = anime.findByAnimeID(animeid);
		/*
		 * 可用的数据：anime类中的字段
		 */
		model.addAllAttributes(aa);
		//判断是否是已定阅的动画
		try {
			String UID = session.getAttribute("USERUID").toString();
			Map<String, Object>isdingyue = select.findIsdingyue(UID, animeid);
			String theanimeid = isdingyue.get("anime_id").toString();
			if (theanimeid.equals(animeid)) {
				//已定阅的情况
				model.addAttribute("isdingyue", "true");
			}else{
				model.addAttribute("isdingyue", "false");
			}
			//找出现在看得集数
			String watchednumber = isdingyue.get("number").toString();
			try {
				//将String装换int
				Integer item = new Integer(watchednumber);
				int watchednumberint = item.intValue();
				//送到页面进行高亮处理
				model.addAttribute("watchednumber", watchednumberint);
			} catch (Exception e) {}
		} catch (Exception e) {}
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
		try{
			String uid = session.getAttribute("USERUID").toString();
			Map<String, Object>map = anime.findByAnimeID(animeid);
			String a = map.get("anime_number").toString();
			useranime.add(animeid, uid, a);
			return "true";
		}catch(Exception e){
			return "false";
		}
		
	}

	/**
	 * 对动画集数进行更新
	 */
	@RequestMapping(value = "/bangumi/{animeid}/{animenumber}", method = RequestMethod.GET)
	public String banguminumberupdata(@PathVariable("animeid")String animeid, 
	@PathVariable("animenumber")String animenumber, HttpSession session){
		try {
			String UID = session.getAttribute("USERUID").toString();
			useranime.updatabumber(animeid, animenumber, UID);
			return "redirect:/bangumi/" + animeid;
		} catch (Exception e) {
			//TODO: handle exception
			return "redirect:/sign_in";
		}
	}
}
