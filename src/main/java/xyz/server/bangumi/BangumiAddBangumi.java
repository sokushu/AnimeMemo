package xyz.server.bangumi;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.bangumi.mysql.bean.Anime;
import xyz.bangumi.mysql.dao.AnimeDao;

/**
 * BangumiAddBangumi
 */
@Controller
@EnableAutoConfiguration
public class BangumiAddBangumi {

    @Autowired
	private AnimeDao anime;

    /**对动画进行添加操作 */
	@RequestMapping(value = "/bangumi/addbangumi", method = RequestMethod.POST)
	public String addbangumiPost(@PathVariable("username")String username, Anime anime1, BindingResult result, 
	HttpServletResponse res) {
		if (result.hasErrors()) {
			/**
			 * 拿到错误信息
			 */
			Map<String, String> maperr = new HashMap<>();
			result.getFieldErrors();
			maperr.put(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			/**返回错误信息 */
			return "maperr";
		} else {
			/**正确的情况下，写入数据库 */
			anime.insert(anime1);
			/**
			 * 跳转回添加页面
			 */
			return "redirect:/id/" + username + "/addbangumi";
		}
	}
	
	@RequestMapping(value = "/bangumi/addbangumi", method = RequestMethod.GET)
	public String addbangumiGet(Model model, HttpSession session) {
		try {
			@SuppressWarnings("unused")
			String UID = session.getAttribute("USERUID").toString();
			return "user/addbangumi";
		} catch (Exception e) {
			//TODO: handle exception
			return "redirect:/sign_in";
		}
		
	}
}