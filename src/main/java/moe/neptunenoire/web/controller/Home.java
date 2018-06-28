package moe.neptunenoire.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.InfoData;
import moe.neptunenoire.web.database.ReoKissMai;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.StringUtil;

public class Home {

	/** 数据库操作 */
	private ReoKissMai reoKissMai;
	/** 字符工具类 */
	private StringUtil stringUtil = new StringUtil();

	public Home(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		this.reoKissMai = new ReoKissMai(maiKissReo, redis);
	}

	/**
	 * 用户访问{"/id/", "/home/"}
	 * 判断用户该访问哪一个链接
	 * @return
	 */
	public boolean showHomeByUID(HttpSession session) {
		Object url = session.getAttribute(InfoData.Session_USERURL);
		return url == null;
	}

	/**
	 * 补全用户主页的URL
	 * @return
	 */
	public String getHomeUrl(HomeType type, HttpSession session) {
		Object url = null;
		String hometype = null;
		if (type == HomeType.UID) {
			hometype = "/home/";
			url = session.getAttribute(InfoData.Session_USERUID);
		}else if (type == HomeType.URL) {
			hometype = "/id/";
			url = session.getAttribute(InfoData.Session_USERURL);
		}
		return url != null ? "redirect:" + hometype + url.toString() : "redirect:/";
	}

	public enum HomeType{
		UID,URL
	}
}
