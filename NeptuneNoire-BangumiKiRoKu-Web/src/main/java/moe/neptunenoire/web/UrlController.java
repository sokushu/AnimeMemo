package moe.neptunenoire.web;

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
import org.springframework.web.multipart.MultipartFile;

import moe.neptunenoire.web.bean.BangumiEditBean;
import moe.neptunenoire.web.bean.SignInBean;
import moe.neptunenoire.web.controller.Bangumi;
import moe.neptunenoire.web.controller.Blog;
import moe.neptunenoire.web.controller.Home;
import moe.neptunenoire.web.controller.Home.HomeType;
import moe.neptunenoire.web.controller.Index;
import moe.neptunenoire.web.controller.Index.AnimeType;
import moe.neptunenoire.web.controller.System;
import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.controller.error.BlogNotFoundException;
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

	/** 首页的类 */
	private Index index;
	/**  */
	private Bangumi bangumi;
	/**  */
	private Home home;

	private System system;

	private Blog blog;

	/**  */
	@Autowired
	public UrlController(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		index = new Index(maiKissReo, redis);
		bangumi = new Bangumi(maiKissReo, redis);
		home = new Home(maiKissReo, redis);
		system = new System(maiKissReo, redis);
		blog = new Blog(maiKissReo, redis);
	}

/*
 * ==========================================================================
 * 字段
 * ==========================================================================
 */
	private enum ModelString{
		/** 是否已经登陆  */IsSign_in,
		/** 显示首页的动画（时间顺序） */IndexAnimeTime,
		/** 显示首页的动画（喜爱顺序） */IndexAnimeLike,
		/** 显示信息 */Info,
		/** 显示集合数据 */Data,
	}
/*
 * ==========================================================================
 * http://localhost/								Index Root（Index.java）
 * ==========================================================================
 */
	/** 展示首页 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(Model model, HttpSession session) {
		{
			/* 返回用户是否已经登陆 （boolean） */
			model.addAttribute(ModelString.IsSign_in.name(), index.IsSign_in(session));
			/* 返回首页的动画 （List<Map<String, Object>>） */
			model.addAttribute(ModelString.IndexAnimeTime.name(), index.getIndexAnime(AnimeType.time));
			/* 返回首页的动画 （List<Map<String, Object>>） */
			model.addAttribute(ModelString.IndexAnimeLike.name(), index.getIndexAnime(AnimeType.like));
		}
		return "indexhome";
	}

	/** 搜索 */
	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public String showSearch(String keyword) {
		return "";
	}

	/** 显示登陆页面 */
	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String showSignIn() {
		return "";
	}

	/** 登陆操作 */
	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public String sendSignIn(SignInBean signInBean, Model model, HttpSession session) {
		{
			/* 得到登陆的信息  (String)*/
			model.addAttribute(ModelString.Info.name(), index.sign_in(signInBean, session));
		}
		//TODO
		return index.IsSign_in(session) ? /*跳转到指定页面*/ "" : /*跳转回登陆页面*/ "";
	}

	/** Ajax登陆操作 */
	@ResponseBody
	@RequestMapping(value = "/sign_in", method = RequestMethod.PUT)
	public String sendSignInAJ(SignInBean signInBean, HttpSession session) {
		/* 得到登陆的信息，用于显示在登陆页面上 */
		return index.sign_in(signInBean, session);
	}

	/** 显示注册页面 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String showSignUp() {
		return "";
	}

	/** 注册操作 */
	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String sendSignUp(Users users) {
		return "";
	}

	/** Ajax注册操作 */
	@ResponseBody
	@RequestMapping(value = "/sign_up", method = RequestMethod.PUT)
	public String sendSignUpAJ(Users users) {
		return "";
	}

	/** 退出登陆 */
	@RequestMapping(value = "/sign_out", method = RequestMethod.GET)
	public String signOut(HttpSession session) {
		session.invalidate();
		return "";
	}

	/** 文件上传 */
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String fileUpLoad(MultipartFile upfile) {
		return "";
	}
/*
 * ==========================================================================
 * http://localhost/bangumi/						Bangumi Root(Bangumi.java)
 * ==========================================================================
 */
	/**
	 * 获得一部动画，漫画等
	 * @throws BangumiNotFoundException
	 */
	@RequestMapping(value = "/bangumi/{bangumiid}", method = RequestMethod.GET)
	public String showBangumi(@PathVariable("bangumiid")String bangumiid, Model model, HttpSession session) throws BangumiNotFoundException {
		{
			model.addAttribute(ModelString.IsSign_in.name(), index.IsSign_in(session));
			model.addAttribute(ModelString.Data.name(), bangumi.showBangumi(bangumiid));

		}
		return "";
	}

	/**  */
	@RequestMapping(value = "/bangumi/{bangumiid}/edit", method = RequestMethod.GET)
	public String showBangumiEdit(Model model, HttpSession session) {
		return "";
	}

	/**  */
	@RequestMapping(value = "/bangumi/{bangumiid}/edit", method = RequestMethod.POST)
	public String sendBangumiEdit(BangumiEditBean bangumiEditBean, HttpSession session) {
		return "";
	}

/*
 * ==========================================================================
 * http://localhost/id/								Home Root(Home.java)
 * ==========================================================================
 */
	/**
	 * 展示个人页面（可变，可定制）
	 */
	@RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
	public String showHome(Model model, @PathVariable("url")String url, HttpSession session) {
		return "";
	}

	/**  */
	@RequestMapping(value = {"/id/", "/home/"}, method = RequestMethod.GET)
	public String showHome(HttpSession session) {
		return home.showHomeByUID(session) ?
			   home.getHomeUrl(HomeType.UID, session) :
			   home.getHomeUrl(HomeType.URL, session);
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
 * http://localhost/Blog							Blog Root(Blog.java)
 * ==========================================================================
 */
	/**
	 *
	 * @param model
	 * @param blogid
	 * @return
	 * @throws BlogNotFoundException
	 */
	@RequestMapping(value = "/blog/{blogid}", method = RequestMethod.GET)
	public String showBlog(Model model, @PathVariable("blogid")String blogid) throws BlogNotFoundException {
		{
			model.addAttribute(ModelString.Data.name(), blog.showBlog(blogid));
		}
		return "";
	}

	@RequestMapping(value = "/blog/addblog", method = RequestMethod.POST)
	public String addBlog(String title, String article, HttpSession session) {
		blog.addBlog(title, article, session);
		return "";
	}

	@RequestMapping(value = "/blog/addblog", method = RequestMethod.GET)
	public String addBlog(Model model) {
		return "";
	}

/*
 * ==========================================================================
 * http://localhost/system							System Root(System.java)
 * ==========================================================================
 */
	/**
	 * 展示系统控制面板
	 */
	@RequestMapping(value = "systeminfo", method = RequestMethod.GET)
	public String showSystem(Model model) {
		{
			model.addAttribute("TotalMemory", system.getTotalMemory());
			model.addAttribute("FreeMemory", system.getFreeMemory());
		}
		return "";
	}

/*
 * ==========================================================================
 * http://localhost/ajax							Ajax Root(Ajax.java)
 * ==========================================================================
 */
}
