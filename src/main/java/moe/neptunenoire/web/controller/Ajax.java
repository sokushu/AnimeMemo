package moe.neptunenoire.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import moe.neptunenoire.web.mysql.MaiKissReo;

@Controller
@RequestMapping(value = "/ajax")
public class Ajax {
    // 获取Session时间
    @ResponseBody
    @RequestMapping(value = "/getsessiontime", method = RequestMethod.POST)
	public String getSessionTime(HttpServletRequest req) {
        return new Integer(req.getSession().getMaxInactiveInterval()).toString();
    }
    // 获取是否登陆
    @ResponseBody
	@RequestMapping(value = "/issign_in", method = RequestMethod.POST)
	public boolean issign_in(HttpServletRequest req) {
		return getsign_in(req);
    }
    // 进行登陆验证
    @ResponseBody
    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public boolean ajaxsign_in(String username, String password){
        return ajax_login(username, password);
    }
    // 返回单个动画的数据 BY ID
    @ResponseBody
    @RequestMapping(value = "/id/{meth}", method = RequestMethod.GET)
    public Map<String, Object> findOneAnime(@PathVariable("meth") String meth, Model model) {
        return mySQL.Anime_FindByAnimeID(meth);
    }
    // 获取全部的动画数据
    @ResponseBody
    @RequestMapping(value = "/anime/getall", method = RequestMethod.GET)
    public List<Map<String, Object>> returnAllAnime(){
    	return mySQL.Anime_ReturnAllAnime();
    }
    @ResponseBody
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public List<Map<String, Object>> search(@PathVariable("name")String name){
    	return mySQL.Anime_SearchAnime(name);
    }
    // 获取首页的最新添加的8个动画
    @ResponseBody
    @RequestMapping(value = "/indexanime", method = RequestMethod.GET)
    public List<Map<String, Object>> returnIndexAnime(){
    	return mySQL.Anime_FindIndexAnime();
    }
    //数据库中查询图片后，返回图片路径
    @RequestMapping(value = "/pic/{picid}", method = RequestMethod.GET)
    public String getPic(@PathVariable("picid")String picid){
        return "";
    }
    /**
     * ===========================================================================
     */
    @Autowired
    private MaiKissReo mySQL;

    private boolean getsign_in(HttpServletRequest req){
        try {
            @SuppressWarnings("unused")
			String a = req.getSession().getAttribute("USERURL").toString();
			return true;
		} catch (Exception e) {
			return false;
		}
    }

	private boolean ajax_login(String username, String password) {
		try {
			Map<String, Object>map = mySQL.User_FindUserByUsername(username);
			String user = map.get("username").toString();
			String pass = map.get("password").toString();
			if (user.equals(username)&&pass.equals(password)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}