package moe.neptunenoire.web.urlsetting;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.HomeInfo;
import moe.neptunenoire.web.util.UtilsPack;


@Controller
@EnableAutoConfiguration
public class Home extends UtilsPack {

	@Autowired
	private MaiKissReo maiKissReo;

	protected String Method_ID = "id";
    protected String Method_Home = "home";

    protected String HomeError = "";
    protected String HomePage = "user/home";

    StringUtil strutil = getStringUtil();

    UtilsPack userid = getUserID("");

    @RequestMapping(value = "/home/{uid}", method = RequestMethod.GET)
    public String getHomeByUID(Model model, @PathVariable("uid")String uid) {
    	{
    		model.addAttribute("", "");

    	}
    	return HomePage;
    }

    @RequestMapping(value = "/id/{url}", method = RequestMethod.GET)
    public String getHomeByURL(Model model, @PathVariable("url")String url) {
    	{
    		model.addAttribute("", "");
    	}
    	return HomePage;
    }

    @RequestMapping(value = "/id/{url}/setting", method = RequestMethod.GET)
    public String getHomeSettingByURL(Model model, @PathVariable("url")String url, HttpSession session) {
    	return "";
    }

    @RequestMapping(value = "/home/{uid}/setting", method = RequestMethod.GET)
    public String getHomeSettingByUID(Model model, @PathVariable("uid")String uid, HttpSession session) {
    	return "";
    }

    @RequestMapping(value = "/id/{url}/setting", method = RequestMethod.POST)
    public String sendHomeSettingByURL(HomeInfo homeinfo) {
    	return "";
    }

    @RequestMapping(value = "/home/{uid}/setting", method = RequestMethod.POST)
    public String sendHomeSettingByUID(HomeInfo homeinfo) {
    	return "";
    }

}