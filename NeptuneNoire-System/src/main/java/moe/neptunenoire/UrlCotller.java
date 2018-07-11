package moe.neptunenoire;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件管理
 * 
 * @author jo
 *
 */
@Controller
@EnableAutoConfiguration
public class UrlCotller {
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(Model model) {
		return "";
	}
	
	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public boolean makeFile(String fileName) {
		return false;
	}
	
}
