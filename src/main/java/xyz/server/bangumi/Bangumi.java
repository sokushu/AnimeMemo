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

import xyz.InfoData;
import xyz.bangumi.core.bangumi.BangumiCore;
import xyz.bangumi.mysql.dao.AnimeDao;
import xyz.bangumi.mysql.dao.SELECT;
import xyz.bangumi.mysql.dao.UserDao;
import xyz.bangumi.mysql.dao.User_AnimeDao;

@Controller
@EnableAutoConfiguration
public class Bangumi extends BangumiCore{
	
	@Autowired
	private AnimeDao anime;
	@Autowired
	private User_AnimeDao useranime;
	@Autowired
	private SELECT select;
	@Autowired
	private UserDao user;

	/**如果输入了bangumi，暂时返回错误页面 */
	@RequestMapping(value = "/bangumi")
	public String bangumi(){
		return BangumiError;
	}
	/**
	 * 查看动画数据，
	 * 对页面进行控制。
	 * @param animeid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.GET)
	public String bangumilist(@PathVariable("animeid")String animeid, Model model, HttpSession session) {
		
		/**验证输入的是否是数字 */
		if (StringToNum(animeid) == 0) {
			return BangumiError;
		} 
		/**数据库查询动画资料 */
		Map<String, Object> aa = anime.findByAnimeID(animeid);

		/**判断是否有实际数据返回 */
		if (aa == null || aa.size() == 0 || aa.toString().trim().equals("")) {
			/**无数据，返回错误页面 */
			return BangumiError;
		}
		/** 可用的数据：anime类中的字段*/
		model.addAllAttributes(aa);


		//判断是否是已定阅的动画
		try {
			/**是否已登录 */
			String UID = session.getAttribute("USERUID").toString();
			/**
			 * 查询登录用户信息
			 */
			Map<String, Object>userInfo = user.findUser(UID);
			Map<String, Object>isdingyue = select.findIsdingyue(UID, animeid); //如果没有订阅这里会出现空指针错误，即便登录也会显示未登录
			if (isdingyue == null || isdingyue.toString().trim().equals("") || isdingyue.size() == 0) {
				/**未订阅的情况 */
				model.addAttribute("isdingyue", "false");
				model.addAttribute("user", userInfo);
				model.addAttribute("isSign_in", "true");
			}else{
				String theanimeid = isdingyue.get("anime_id").toString();
				if (theanimeid.equals(animeid)) {
					//已定阅的情况
					model.addAttribute("isdingyue", "true");
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
				/**显示登录后才会显示的项目 */
				model.addAttribute("user", userInfo);
				model.addAttribute("isSign_in", "true");
			}
		} catch (Exception e) {
			/**未登录的情况 */
			/**不显示登录后才显示的项目 */
			model.addAttribute("isSign_in", "false");
		}
		return BangumiShow;
	}
	/**使用ajax请求 */
	/**
	 * 订阅动画
	 * @return
	 */
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.POST)
	@ResponseBody
	public boolean bangumiSubscriber(@PathVariable("animeid")String animeid, HttpSession session) {
		try{
			/**判断是否是数字 */
			if (StringToNum(animeid) == 0) {
				return false;
			}
			/**得到用户ID */
			String uid = session.getAttribute("USERUID").toString();

			Map<String, Object>map = anime.findByAnimeID(animeid);

			/**判断是否有数据 */
			if (map == null || map.size() == 0 || map.toString().trim().equals("")) {
				return false;
			}
			boolean flag = isDingYueed(uid, animeid);
			if (flag) {
				/**非法订阅的情况，停止程序执行 */
				return false;
			}else{
				/**未订阅的情况,订阅动画 */
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
			/**判断是否是数字 */
			int a = StringToNum(animenumber);//请求更新的动画集数
			if (StringToNum(animeid) == 0) {
				return BangumiError;
			}else if (a == 0) {
				return "redirect:/bangumi/" + animeid;
			}

			/**得到用户ID */
			String UID = session.getAttribute("USERUID").toString();

			/**对用户请求的集数进行验证 */
			Map<String, Object>animeMap = anime.findByAnimeID(animeid);
			/**是否能够请求到数据 */
			if (animeMap == null || animeMap.size() == 0 || animeMap.toString().trim().equals("")) {
				/**无数据，返回错误页面 */
				return BangumiError;
			}
			/**如果没有订阅就直接点击对动画进行更新的策略 */
			if (isDingYueed(UID, animeid)) {
				/**已经订阅的情况 */
				/**查询动画总集数 */
				String allnumber = animeMap.get("anime_number").toString();
				/**装换数据 */
				int allnumberint = StringToNum(allnumber);
				/**如果请求集数超过总集数 */
				if (allnumberint < a) {
					//更新的集数是动画的最终话
					useranime.updatabumber(animeid, allnumber, UID);
				}else{
					//更新的集数是请求集数
					useranime.updatabumber(animeid, animenumber, UID);
				}
			}else{
				/**如果没订阅则会无动作 */
			}
			return "redirect:/bangumi/" + animeid;
		} catch (Exception e) {
			//TODO: handle exception
			return "redirect:/sign_in";
		}
	}
	/**
	 * 使用ajax进行动画更新
	 *  */
	@RequestMapping(value = "/bangumi/{animeid}/{animenumber}", method = RequestMethod.POST)
	@ResponseBody
	public String BanguminumberupdataPOST(@PathVariable("animeid")String animeid,@PathVariable("animenumber")String animenumber, HttpSession session){

		int a = StringToNum(animenumber);
		if (StringToNum(animeid) == 0) {
			return "false";
		}else if (a == 0) {
			return "false";
		}else{

			String useruid = session.getAttribute(InfoData.Session_USERUID).toString();
			Map<String, Object>animeMap = anime.findByAnimeID(animeid);

			if (animeMap == null || animeMap.size() == 0 || animeMap.toString().trim().equals("")) {
				return BangumiError;
			}
			if (isDingYueed(useruid, animeid)) {
				
				String allnumber = animeMap.get("anime_number").toString();
				int allnumberint = StringToNum(allnumber);
				if (allnumberint < a) {
					useranime.updatabumber(animeid, allnumber, useruid);
				}else{
					useranime.updatabumber(animeid, animenumber, useruid);
				}
				return "true";
			}else{
				return "还没有订阅动画哦";
			}

		}
	}
	/**
	 * 判断动画是否订阅
	 */
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
}
