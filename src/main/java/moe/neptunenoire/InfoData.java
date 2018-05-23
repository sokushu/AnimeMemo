package moe.neptunenoire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这是一个数据存放处
 */
public class InfoData {

    /** 用户uid */
    public static String Session_USERUID = "USERUID";
    /** 用户用户名 */
    public static String Session_USERNAME = "USERNAME";
    /** 用户自定义url */
    public static String Session_USERURL = "USERURL";
    /** 用户邮箱 */
    public static String Session_UserEmail = "UserEMAIL";
    /** 用户头像 */
    public static String Session_UserPic = "UserPic";
    /** 用户自定义信息 */
    public static String Session_UserInfo = "UserInfo";
    /** 用户对外显示的名称 */
    public static String Session_UserPageName = "userPageName";
    /** 用户自定义的个人主页背景 */
    public static String Session_UserBickPic = "userbackpic";


    /** 用户个人主页访问使用的方法（home|id） */
    public static String Session_USERMETHOD = "USERMETHOD";

    public static final String MethodHome = "home";
    public static final String MethodID = "id";

    /**
     * 用户权限
     */
    public static String Session_USERYURI = "USERYURI";//用户权限

    /**
     * 权限名称
     */
    /** 123 */
    public static String Yuri_yuri = "yuri";//管理员权限
    public static String Yuri_0 = "0"; //刁民权限
    public static String Yuri_1 = "1"; //普通权限
    public static String Yuri_2 = "2"; //上等人权限
    public static String Yuri_3 = "3"; //洋大人权限

    /**
     * 权限状态
     */
    public static final boolean OnLine = true; // 相当于True
    public static final boolean offLine = false; // False


    /**
     * 拦截器网址保存
     */
    /** 未登录禁止进入 */
    private static List<String> un_SignIN = new ArrayList<>();
    /** 需要相应权限 */
    private static Map<String, List<String>> yuri = new HashMap<>();

    /**
     * 输入权限的时候比较的是该权限是否可以进入
     * @param yuristr 用户的权限
     * @param link 进入的网址
     */
    public static boolean goFilter(String yuristr, String link){
        synchronized(yuri){
            if (yuristr != null) {
                // 该权限下可以访问的网页集
                List<String> yuriList = yuri.get(yuristr);
                // 如果有，则返回True，没有则返回False
                return yuriList.contains(link);
            }else{

            }
            return false;
        }
    }

    /**
     * 是否属于需要登陆的网址
     * @param link 网址
     */
    public static boolean goFilter(String link){
        return un_SignIN.contains(link);
    }
}