package moe.neptunenoire.web.urlsetting;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.controller.BangumiList;
import moe.neptunenoire.web.mysql.MaiKissReo;

/**
 * 显示TAG，文章，小记事。
 * 动画信息等
 */
@Controller
@EnableAutoConfiguration
public class Bangumi extends BangumiList{

	@Autowired
	public Bangumi(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis){
		super(maiKissReo, redis);
	}
	/**
	 * URL路由
	 */
	/**如果输入了bangumi，暂时返回错误页面 */
	@RequestMapping(value = "/bangumi")
	public String bangumi(){
		return BangumiError;
	}
	// 详细的动画页面
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.GET)
	public String bangumiAnimeid(@PathVariable("animeid")String animeid, Model model, HttpSession session){
		return bangumilist(animeid, model, session);
	}
	// 订阅一部动画
	@RequestMapping(value = "/bangumi/{animeid}", method = RequestMethod.POST)
	@ResponseBody
	public boolean bangumiadd(@PathVariable("animeid")String animeid, HttpSession session){
		return bangumiSubscriber(animeid, session);
	}
	@RequestMapping(value = "/bangumi/{animeid}/{animenumber}", method = RequestMethod.GET)
	public String bangumiupdata(@PathVariable("animeid")String animeid, @PathVariable("animenumber")String animenumber, HttpSession session){
		return banguminumberupdata(animeid, animenumber, session);
	}
	@RequestMapping(value = "/bangumi/{animeid}/{animenumber}", method = RequestMethod.POST)
	@ResponseBody
	public String Bangumiupdataajax(@PathVariable("animeid")String animeid,@PathVariable("animenumber")String animenumber, HttpSession session){
		return BanguminumberupdataPOST(animeid, animenumber, session);
	}
	/**
	 * =======================================================================
	 */

}
