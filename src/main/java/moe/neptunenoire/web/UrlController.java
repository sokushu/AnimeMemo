package moe.neptunenoire.web;

import java.util.List;
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

import moe.neptunenoire.web.bean.SignInBean;
import moe.neptunenoire.web.controller.Index;
import moe.neptunenoire.web.controller.Index.AnimeType;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Users;

/**
 * <pre>
 * showIndex()		GET
 * sendIndex()		POST
 * putIndex()		PUT
 * deleteIndex()	DELETE
 *
 * showIndexAJ()	GET
 * sendIndexAJ()	POST
 * putIndexAJ()		PUT
 * deleteIndexAJ()	DELETE
 * </pre>
 *
 * @author M
 *
 */
@Controller
@EnableAutoConfiguration
public class UrlController {
	
	/**
	 * 首页的类
	 */
	private Index index;

	@Autowired
	public UrlController(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		index = new Index(maiKissReo, redis);
	}
	
/*
 * ==========================================================================
 * 字段
 * ==========================================================================
 */
	/** 是否已经登陆  */
	private final String IsSign_in = "IsSign_in";
	
	/** 显示首页的动画（时间顺序） */
	private final String IndexAnimeTime = "IndexAnimeTime";
	
	/** 显示首页的动画（喜爱顺序） */
	private final String IndexAnimeLike = "IndexAnimeLike";
/*
 * ==========================================================================
 * http://localhost/								Index Root（Index.java）
 * ==========================================================================
 */
	/**
	 * 展示首页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(Model model, HttpSession session) {
		{
			/* 返回用户是否已经登陆 （boolean） */
			model.addAttribute(IsSign_in, index.IsSign_in(session));
			/* 返回首页的动画 （List<Map<String, Object>>） */
			model.addAttribute(IndexAnimeTime, index.getIndexAnime(AnimeType.time));
		}
		return "";
	}
	
	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public String showSearch(String keyword) {
		return "";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String showSignIn() {
		return "";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public String sendSignIn(SignInBean signInBean) {
		{
			
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sign_in", method = RequestMethod.PUT)
	public String sendSignInAJ(SignInBean signInBean, HttpSession session) {
		/* 得到登陆的信息，用于显示在登陆页面上 */
		return index.sign_in(signInBean, session).get("info");
	}

	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String showSignUp() {
		return "";
	}

	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String sendSignUp(Users users) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sign_up", method = RequestMethod.PUT)
	public String sendSignUpAJ(Users users) {
		return "";
	}
	
	@RequestMapping(value = "/sign_out", method = RequestMethod.GET)
	public String signOut() {
		return "";
	}
/*
 * ==========================================================================
 * http://localhost/bangumi/						Bangumi Root(Bangumi.java)
 * ==========================================================================
 */
	
	
	
/*
 * ==========================================================================
 * http://localhost/id/								Home Root(Home.java)
 * ==========================================================================
 */
	/**
	 * 展示个人页面（可变，可定制）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
	public String showHome(Model model, @PathVariable("url")String url, HttpSession session) {
		return "";
	}
	
	/**
	 * 通过ID展示个人主页(固定)
	 * @return
	 */
	@RequestMapping(value = "/home/{id}", method = RequestMethod.GET)
	public String showHomeByID(Model model, @PathVariable("url")String url, HttpSession session) {
		return "";
	}
	
/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
	
	
	
/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
	
	
	
/*
 * ==========================================================================
 * http://localhost/system							System Root(System.java)
 * ==========================================================================
 */
	/**
	 * 展示系统控制面板
	 * @param model
	 * @return
	 */
	public String showSystem(Model model) {
		return "";
	}
}
