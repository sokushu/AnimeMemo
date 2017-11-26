package xyz.server.api;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/api")
public class API {

	@RequestMapping(value = "/getsessiontime")
	public String getSessionTime(HttpServletRequest req) {
		String a = new Integer(req.getSession().getMaxInactiveInterval()).toString();
		return a;
	}
	
	@RequestMapping(value = "/issign_in")
	public String getUserLigin(HttpServletRequest req) {
		try {
			@SuppressWarnings("unused")
			String a = req.getSession().getAttribute("USERURL").toString();
			return "true";
		} catch (Exception e) {
			// TODO: handle exception
			return "false";
		}
	}
}
