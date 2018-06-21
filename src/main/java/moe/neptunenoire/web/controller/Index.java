package moe.neptunenoire.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;

import moe.neptunenoire.InfoData;
import moe.neptunenoire.web.bean.SignInBean;
import moe.neptunenoire.web.database.DataSet;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.util.MD5Coding;
import moe.neptunenoire.web.util.StringUtil;
import moe.neptunenoire.web.util.UserID;

/**
 *
 * @author M
 *
 */
public class Index {

	/** 数据库字段 */
	private MaiKissReo maiKissReo;
	/** 内存数据库操作 */
	private DataSet dataSet;
	/** 字符工具类 */
	private StringUtil stringUtil = new StringUtil();

	/**
	 * 初始化
	 */
	public Index(MaiKissReo maiKissReo, RedisTemplate<String, Map<String, Object>> redis) {
		this.maiKissReo = maiKissReo;
		this.dataSet = new DataSet(maiKissReo, redis);
	}

	/**
	 * 判断用户是否已经登陆，如果没登陆，
	 * 返回False，如果登陆，返回True
	 * @return
	 */
	public boolean IsSign_in(HttpSession session) {
		Object mysess = session.getAttribute(InfoData.Session_USERNAME);
		return mysess != null;
	}

	/**
	 * 获取首页的动画
	 * 按收藏展示，按评分展示，按最新展示
	 * @return
	 */
	public List<Map<String, Object>> getIndexAnime(AnimeType type){
		//TODO 首页动画的展示
		maiKissReo.Anime_FindIndexAnime(8);
		return null;
	}

	public enum AnimeType{
		time,like
	}

	/**
	 * 用户登陆，将用户信息写入Session
	 * @param signInBean
	 * @return
	 */
	public String sign_in(SignInBean signInBean, HttpSession session) {

		String username = signInBean.getUsername();
		String password = signInBean.getPassword();

		/* 生成UID */
		UserID uid = new UserID(username);

		Map<String, Object> userdata = dataSet.getUser(uid.GetlongCode());

		/* 如果没有缓存过 */
		if (stringUtil.isNull(userdata)) {
			userdata = maiKissReo.User_FindUserByID(uid.toString());
			if (!stringUtil.isNull(userdata)) {
				/* 缓存用户数据 */
				dataSet.saveUsersData(userdata);
			}else {
				/* 没有这个用户 */
				return "用户名或密码错误";
			}
		}

		/* 获取MD5密码 */
		String md5Password = new MD5Coding().coding(username, password, uid.GetlongCode());

		/* 验证密码 */
		if (((String)userdata.get("password")).equals(md5Password)) {
			/* 将用户数据写入Session */
			session.setAttribute(InfoData.Session_USERNAME, userdata.get("username"));
	        session.setAttribute(InfoData.Session_UserPageName, userdata.get("pageusername"));
	        session.setAttribute(InfoData.Session_UserBickPic, userdata.get("backpic"));
	        session.setAttribute(InfoData.Session_UserEmail, userdata.get("email"));
	        session.setAttribute(InfoData.Session_UserInfo, userdata.get("info"));
	        session.setAttribute(InfoData.Session_UserPic, userdata.get("userpic"));
	        session.setAttribute(InfoData.Session_USERUID, userdata.get("uid"));
	        // session.setAttribute(InfoData.Session_USERYURI, userInfo.get("username"));
            if (stringUtil.isNull((String)(userdata.get("url")))) {
                session.setAttribute(InfoData.Session_USERURL, userdata.get("uid"));
                session.setAttribute(InfoData.Session_USERMETHOD, InfoData.MethodHome);
            }else{
                session.setAttribute(InfoData.Session_USERURL, userdata.get("url"));
                session.setAttribute(InfoData.Session_USERMETHOD, InfoData.MethodID);
            }
		}else {
			/* 密码错误 */
			return "用户名或密码错误";
		}

		return "登陆成功";
	}

}
