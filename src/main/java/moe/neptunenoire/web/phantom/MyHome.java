package moe.neptunenoire.web.phantom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import moe.neptunenoire.InfoData;
import moe.neptunenoire.web.mysql.MaiKissReo;


/**
 * 我的个人主页
 *
 */
public class MyHome {

    /** Session */
    @Autowired
    private HttpSession session;

    /** 数据库连接 */
    @Autowired
    private MaiKissReo maiReo;

    /** 用户缓存 */
    final private static Map<String, Map<String, Object>> userMap = new LinkedHashMap<>();

    /**
     * 进行运行时的
     * 初始化配置操作
     */
    public MyHome(){

    }

    /**
     * 单纯访客访问
     * （包含未登录用户）
     * @param url 访问地址
     * @param type 访问的类型（id？home？）
     *
     * @return 返回 处理好的数据，在Controller中渲染
     */
    public Map<String, Object> showMyHome(String url, String type){

        /**
         * 将要以何种方式打开
         * hostname/id/url
         * hostname/home/7123456789
         */
        String userMethod = null;
        /** 访客的用户名 */
        String userName = null;
        /** 访客UID */
        String uid = null;
        /** 访客url */
        String uurl = null;
        /** 被访者用户名 */
        String homeUserName = null;
        /** 被访者UID */
        String homeUid = null;
        /** 是否登陆 False 否 True 是 */
        boolean isSign_in = false;
        /** 访问的这个页面是否是自己的 False 否 True 是 */
        boolean isMe = false;

        Map<String, Object> returnMap = new HashMap<>();
        //==============================================================
        // Session检查
        try {
            uurl = session.getAttribute(InfoData.Session_USERURL).toString();
            userName = session.getAttribute(InfoData.Session_USERNAME).toString();
            uid = session.getAttribute(InfoData.Session_USERUID).toString();
            // 如果没出错就是登陆了
            isSign_in = true;
            // 捕获Null
        } catch (Exception e) {
            // 出错，未登录
            isSign_in = false;
            isMe = false;
        }

        // 如果访问的是自己的页面
        if (uurl != null && url.equals(uurl)) {
        	isMe = true;

        	Map<String, Object> userinfo = null;//getUserInfo(uurl);
        	if (userinfo == null) {
				
			}
        	returnMap.put("", isMe);
        }

        // Home的方式
        if (InfoData.MethodHome.equals(type)) {
            try {
                Map<String, Object> userinfo = null;//getUserInfo(url);
                if (userinfo == null) {
                    userinfo = maiReo.User_FindUserByID(url);
                    if (userinfo == null) {
                        return null;
                    }else{
                        //saveUserInfo(url, userinfo);
                    }
                }
                returnMap = userinfo;
                return userinfo;
            } catch (Exception e) {
                // 出错的情况，一般是不存在
            	return null;
            }
        }
        // id的方式
        if (InfoData.MethodID.equals(type)) {
            try {
                Map<String, Object> userInfo = null;//getUserInfo(url);
                if (userInfo == null) {
                    userInfo = maiReo.User_FindUserByShowByURL(url);
                    if (userInfo == null) {
                        return null;
                    }else{
                        //saveUserInfo(url, userInfo);
                    }
                }
                return userInfo;
            } catch (Exception e) {
                // 出错的情况，一般是不存在
            	return null;
            }
        }

        return null;
    }

    /**
     * 用于显示用户的订阅番组
     */
    public List<String> showMyBangumi(){

        return null;
    }
}