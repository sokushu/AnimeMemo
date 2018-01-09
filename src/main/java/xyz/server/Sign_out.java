package xyz.server;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class Sign_out {
	
	@RequestMapping(value = "/sign_out", method = RequestMethod.GET)
	public String sign_out(HttpSession session) {
		/**用户退出 */
		/**消除Session */
		session.invalidate();
		/**返回首页 */
		return "redirect:/";
	}

}
