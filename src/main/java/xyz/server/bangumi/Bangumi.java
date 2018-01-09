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
import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.User_AnimeDao;
import xyz.server.admin.Properties;

@Controller
@EnableAutoConfiguration
public class Bangumi{
	
	@Autowired
	private AnimeDao anime;
	@Autowired
	private User_AnimeDao useranime;
	@Autowired
	private SELECT select;
	@Autowired
	private AnimeDao animedao;

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
	public boolean bangumiSubscriber(@PathVariable("animeid")String animeid, HttpSession session) {
		try{
			/**得到用户ID */
			String uid = session.getAttribute("USERUID").toString();
			boolean flag = isDingYueed(uid, animeid);
			if (flag) {
				/**非法订阅的情况，停止程序执行 */
				return false;
			}else{
				/**未订阅的情况,订阅动画 */
				Map<String, Object>map = anime.findByAnimeID(animeid);
				String a = map.get("anime_number").toString();
				useranime.add(animeid, uid, a);
			}
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	/**对动画集数进行更新 */
	@RequestMapping(value = "/bangumi/{animeid}/{animenumber}", method = RequestMethod.GET)
	public String banguminumberupdata(@PathVariable("animeid")String animeid, 
	@PathVariable("animenumber")String animenumber, HttpSession session){
		try {
			String UID = session.getAttribute("USERUID").toString();
			/**如果没有订阅就直接点击对动画进行更新的策略 */
			if (isDingYueed(UID, animeid)) {
				/**如果返回true则是已经订阅 */
				useranime.updatabumber(animeid, animenumber, UID);
			}else{
				/**如果没订阅则会无动作 */
			}
			return "redirect:/bangumi/" + animeid;
		} catch (Exception e) {
			//TODO: handle exception
			return "redirect:/sign_in";
		}
	}

	/**判断是否是空 */
	private boolean isDingYueed(String uid, String animeid){
		try {
			/**判断动画是否已定阅（非法订阅） */
			/**获取订阅信息 */
			Map<String, Object>isdingyue = select.findIsdingyue(uid, animeid);
			if (isdingyue == null || isdingyue.toString().equals("")) {
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			//TODO: handle exception
			/**如果出错证明没有订阅 */
			return false;
		}
	}
	/**
	 * 对动画进行修改操作
	 */
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.POST)
	public String edit(@PathVariable("animeid")String animeid, Anime anime) {
		animedao.updata(animeid, anime);
		return "redirect:/bangumi/{" + animeid + "}";
	}
	/**
	 * 得到动画的修改页面
	 */
	@RequestMapping(value = "/bangumi/{animeid}/bangumiedit", method = RequestMethod.GET)
	public String bangumiedit(@PathVariable("animeid")String animeid, Model model) {
		Map<String, Object> map = animedao.findByAnimeID(animeid);
		model.addAllAttributes(map);
		return "/bangumi/bangumiedit";
	}
}
