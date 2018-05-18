package moe.neptunenoire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.mysql.bean.Anime;
import moe.neptunenoire.mysql.bean.Users;
import moe.neptunenoire.phantom.index.Index;

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
     * 用户注册
     * @param user 用户注册数据
     * @return 注册成功或失败
     */
    @RequestMapping(value = sign_up, method = RequestMethod.POST)
    public String sign_up(Users user , Model model){
        int code = signUpPost(user);
        {
            {
                model.addAttribute("haserror", code != 200 ? true:false);
                model.addAttribute("error", signNo(code));
            }
        }
        return code != 200 ? showSign_up : "redirect:" + sign_in;
    }

    /**
     * 显示注册页面
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
     * 退出登录
     */
    @RequestMapping(value = sign_out, method = RequestMethod.GET)
	public String sign_out(HttpSession session) {
		signOut(session);
		return "redirect:" + index;
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
     * 使用Ajax进行登陆验证
     */
	@RequestMapping(value = sign_in, method = RequestMethod.PUT)
	@ResponseBody
	public boolean Sign_inajax(String username, String password){
        return Sign_inajaxImpl(username, password);
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
    public String addBangumi(Model model){
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
        return "adminImpl(adminusername, adminpassword, session);";
    }
    
    /** 
     * 显示管理员登陆页面
     */
    @RequestMapping(value = Server + "/admin", method = RequestMethod.GET)
    public String showAdmin(){
        return "";
    }
    
    
}