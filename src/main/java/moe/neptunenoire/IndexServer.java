package moe.neptunenoire;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.InfoData;
import moe.neptunenoire.mysql.bean.Anime;
import moe.neptunenoire.mysql.bean.Users;
import moe.neptunenoire.mysql.dao.MaiKissReo;
import moe.neptunenoire.mysql.dao.MySQL;
import moe.neptunenoire.phantom.index.Index;
import moe.neptunenoire.util.UserID;

@Controller
@EnableAutoConfiguration
public class IndexServer extends Index {
    /** 网址路由 */
    public static final String index = "/";
    public static final String sign_in = "/sign_in";
    public static final String sign_up = "/sign_up";
    public static final String sign_out = "/sign_out";
    public static final String search = "/s";
    public static final String fileupload = "/fileupload";
    public static final String Server = "/server";

    /** 用于显示的网页文件 */
    /** 首页文件 */
    public static final String indexFile = "indexhome";
    /** 登陆网页显示 */
    public static final String showSign_in = "users/sign_in";
    /** 显示注册用网页 */
    public static final String showSign_up = "users/sign_up";

    /**
     * 显示首页
     * @param model 用于渲染首页
     * @param session 用于判断用户是否登陆
     */
    @RequestMapping(value = index, method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        {
            {
                // 用于显示首页的动画
                model.addAttribute("indexanime", index_Anime());
                // 用户是否登陆 布尔类型(true false)
                model.addAttribute("issign_in", is_SignIn(session));
                // 首页轮播图片
                model.addAttribute("ad", "/img/page/backpic.jpg");  
            }
            
        }
        return indexFile;
    }

    /**
     * 文件上传
     */
    @RequestMapping(value = fileupload, method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, 
    @RequestHeader HttpHeaders headers, HttpSession session){
		return uploadImpl(request, response, headers, session);
    }

    /**
     * 用户注册
     * @param user 用户注册数据
     * @return 注册成功或失败
     */
    @RequestMapping(value = sign_up, method = RequestMethod.POST)
    public String sign_up(Users user , Model model){
        // 如果注册成功 ：如果注册失败
        return signUp(user) ? "redirect:" + sign_in : signNo(model, showSign_up);
    }

    /**
     * 显示登陆页面
     */
    @RequestMapping(value = sign_up, method = RequestMethod.GET)
	public String signUpGet() {
		return showSign_up;
    }

    /**
     * 登陆操作
     */
    @RequestMapping(value = sign_in, method = RequestMethod.POST)
    public String sign_in(String username, String password, HttpSession session, @RequestHeader HttpHeaders headers, Model model){
        int code = sign_inimpl(username, password, session, headers, model);
        {
            {
                // 设置登录错误信息
                model.addAttribute("errorinfo", getCodeinfo(code));
            }
        }
        return code(code, session);
    }

    /** 
     * 显示登陆页面
     */
    @RequestMapping(value = sign_in, method = RequestMethod.GET)
	public String sign_in_get(Model model) {
        {
            {
                model.addAttribute("attributeName", "attributeValue");
            }
        }
		return showSign_in;
    }

	/**
     * 使用Ajax进行登陆验证
     */
	@RequestMapping(value = sign_in, method = RequestMethod.PUT)
	@ResponseBody
	public boolean Sign_inajax(String username, String password){
        return Sign_inajaxImpl(username, password);
    }
    
    /**
     * 退出登录
     */
    @RequestMapping(value = sign_out, method = RequestMethod.GET)
	public String sign_out(HttpSession session) {
		session.invalidate();
		return "redirect:" + index;
    }
    
    /**
     * 进行动画的搜索操作
     */
	@RequestMapping(value = search, method = RequestMethod.GET)
	public String Search(String w, Model model, String page){
        return searchImpl(w, model, page);
    }
    
    /**
     * 显示添加数据的页面
     * 番组，-》动画，电影小说等
     */
    @RequestMapping(value = Server + "/add", method = RequestMethod.GET)
    public String addBangumi(){
        return "add_data";
    }
    
    /** 
     * 添加数据
     * 番组，-》动画，电影小说等
     */
    @RequestMapping(value = Server + "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addBangumiPost(Anime anime){
        return addbangumiPostImpl(anime);
    }

    /**
     * 用于管理员登陆
     */
    @RequestMapping(value = Server + "/admin", method = RequestMethod.POST)
    public String admin(String adminusername, String adminpassword, HttpSession session){
        return adminImpl(adminusername, adminpassword, session);
    }
    
    /** 
     * 显示管理员登陆页面
     */
    @RequestMapping(value = Server + "/admin", method = RequestMethod.GET)
    public String showAdmin(){
        return "";
    }
    /**
     * ==============================================================
     * 代码实现
     * ==============================================================
     */

    @Autowired
    private MySQL mysql;
    @Autowired
    private MaiKissReo maireo;

    /**
     * ==============================================================
     * 管理员登陆
     * ==============================================================
     */
    // 闲的无聊，写一下很复杂的验证
    private String adminImpl(String adminusername, String adminpassword, HttpSession session) {
        // 管理员用户名
        String user = "miru";
        // 管理员密码
        String pass = "guk8ne";
        // 进行验证
        if (user.equals(adminusername) && pass.equals(adminpassword)) {
            try {
                // 读取Session中的权限信息
                String yuri = session.getAttribute(InfoData.Session_USERYURI).toString();
                // 管理员登陆
                if (InfoData.Yuri_yuri.equals(yuri)) {
                    session.setAttribute(InfoData.Yuri_yuri, InfoData.OnLine);
                }
            } catch (Exception e) {
                // 刁民也想尝试登陆？拦下
                return "redirect:/";
            }
        }
        return "";
    }
    /**
     * ==============================================================
     * 添加网站数据用
     * 
     * 添加例如动画数据，电影漫画数据，小说数据等
     * ==============================================================
     */
    private String addbangumiPostImpl(Anime anime){
        /**
         * 电视类
         * 动画，电影，剧场版，电视剧
         */
        // if ("".equals(type)) {
            
        
        // }
        /**
         * 书报类
         * 漫画，小说，杂志
         */
        // if ("condition".equals(type)) {
            
        // }
        
        maireo.Anime_AddAnime(anime);
        return "OK";
    }
    /**
     * ==============================================================
     * 用户注册
     * ==============================================================
     */
    private String signUpPost(Users user) {

        StringBuffer sb = new StringBuffer();

		try {
			//人为制造空指针
            String username = maireo.User_FindUserByUsername(user.getUsername()).get("username").toString();
            
            String url = maireo.User_FindUserByShowByURL(user.getUrl()).get("url").toString();
            //返回一个错误信息
			return ""; 
		} catch (Exception e) {
            //我这里的想法是，使用正则表达式，对录入的数据进行检查

            String email = user.getEmail();
            String username = user.getUsername();
            String url = user.getUrl();

            String email_RegEx = "^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}$";
            String username_RegEx = "^[A-Za-z][A-Za-z1-9_-]+$";
            String url_RegEx = "";

            Matcher emailMatcher = Pattern.compile(email_RegEx).matcher(email);;
            Matcher usernameMatcher = Pattern.compile(username_RegEx).matcher(username);
            Matcher urlMatcher = Pattern.compile(url_RegEx).matcher(url);

            if (!emailMatcher.find()) {
                //返回错误信息，想直接返回html代码
                sb.append("");
            }
            if (!usernameMatcher.find()) {
                //返回错误信息
                sb.append("");
            }
            if (!urlMatcher.find()) {
                //返回错误信息
                sb.append("");
            }

            //生成UID
            UserID userid = new UserID(username);
            user.setUid(userid.GetlongCode());

            //如果全部通过，则进行注册
            maireo.User_AddUser(user);

			return isNull(sb.toString()) ? "true" : sb.toString();
		}
    }
    

    /**
     * 使用Ajax进行登陆
     */
    private boolean Sign_inajaxImpl(String username, String password){
        Map<String, Object>readuser = mysql.User_FindUserByUsername(username);
		if (isNull(readuser) == false) {
			String Mapusername = readuser.get("username").toString();
			String Mappassword = readuser.get("password").toString();
			if (Mapusername.equals(username) && Mappassword.equals(password)) {
				//验证成功
				return true;
			}else{
                return false;
            }
		}else{
            return false;
        }
    }
    /**
     * ==============================================================
     * 搜索
     * ==============================================================
     */
    private String searchImpl(String w, Model model, String page) {
		/**搜索各种信息，动画，用户等 */
        int Page = StringToNum(page);

        PageHelper.startPage(Page, 9);
		/**对动画进行搜索 */
		List<Map<String, Object>>search = mysql.Anime_SearchAnime(w);
		if (search.size() == 0) {
			/**如果没有搜索结果的情况 */
			model.addAttribute("ishas", "false");
			/**还需要对HTML文件进行进一步的加工 */
		}else{
			/**如果有搜索结果的话 */
			/**对返回的结果进行分页（使用了一个分页插件） */
			PageInfo<Map<String, Object>> pageinfo = new PageInfo<>(search);
			/**用来指示是否有搜索结果 */
			model.addAttribute("ishas", "true");
			model.addAttribute("title", w);
			model.addAttribute("list", search);
			
			//分页信息
			//得到第一页
			model.addAttribute("pagefirst", 1);
			//上一页
			model.addAttribute("pageone", pageinfo.getPrePage());
			//当前页
			model.addAttribute("pagenow", pageinfo.getPageNum());
			//下一页
			model.addAttribute("pagetwo", pageinfo.getNextPage());
			//最后一页
			model.addAttribute("pagelast", pageinfo.getNavigateLastPage());
			model.addAttribute("kazu", pageinfo.getTotal());
			model.addAttribute("isnext", pageinfo.isHasNextPage());
			model.addAttribute("ispre", pageinfo.isHasPreviousPage());
        }
        
		return "search";
    }
    
}