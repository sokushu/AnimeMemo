package moe.neptunenoire.web.phantom;

import java.util.ArrayList;
import java.util.HashMap;
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
    private static Map<String, Map<String, Object>> map = new HashMap<>();
    
    /** 用户缓存 */
    private static List<String> list = new ArrayList<>();

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
    public List<String> showMyHome(String url, String type){
        
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

        /**
         * 用于返回数据的LIST
         * 【0】：
         */
        List<String> returnList = new ArrayList<>();
        //==============================================================
        // Session检查
        try {
            uurl = session.getAttribute(InfoData.Session_USERURL).toString();
            userName = session.getAttribute(InfoData.Session_USERNAME).toString();
            uid = session.getAttribute(InfoData.Session_USERUID).toString();
            // 如果没出错就是登陆了
            isSign_in = true;
        } catch (Exception e) {
            // 出错，未登录
            isSign_in = false;
            isMe = false;
        }

        // 如果访问的是自己的页面
        if (uurl != null && url.equals(uurl)) {
            
        }

        // Home的方式
        if (InfoData.MethodHome.equals(type)) {
            try {
                Map<String, Object> userinfo = getUserInfo(url);
                if (userinfo == null) {
                    userinfo = maiReo.User_FindUserByID(url);
                    if (userinfo == null) {
                        return null;
                    }else{
                        saveUserInfo(url, userinfo);
                    }
                }
            } catch (Exception e) {
                // 出错的情况，一般是不存在
            }
        }
        // id的方式
        if (InfoData.MethodID.equals(type)) {
            try {
                Map<String, Object> userInfo = getUserInfo(url);
                if (userInfo == null) {
                    userInfo = maiReo.User_FindUserByShowByURL(url);
                    if (userInfo == null) {
                        return null;
                    }else{
                        saveUserInfo(url, userInfo);
                    }
                }
                userInfo.get("key");
            } catch (Exception e) {
                // 出错的情况，一般是不存在
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

    /**
     * 
     */
    public static void saveUserInfo(String url, Map<String, Object> map){
        synchronized(list){
            // 只缓存1000条数据
            for (int i = list.size(); i >= 1000; i--) {
                MyHome.map.remove(list.remove(0));
            }
            list.add(url);
            MyHome.map.put(url, map);
        }
    }

    /**
     * 
     */
    public static Map<String, Object> getUserInfo(String url){
        synchronized(map){
            return map.get(url);
        }
    }
}