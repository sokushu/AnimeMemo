package xyz.server.index;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.InfoData;
import xyz.bangumi.mysql.dao.UserDao;

@Controller
@EnableAutoConfiguration
public class Sign_in{
	
	@Autowired
	private UserDao user;
	/**
	 * 这个不可以进行ajax请求
	 */
	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public void sign_in(String username, String password, HttpSession session, @RequestHeader HttpHeaders headers, HttpServletResponse response, String from) throws IOException {
		try {
			// String to = headers.get("Referer").toString();
			
			// String les1go = to.substring(to.indexOf("=") + 1);
			// String lesgo = les1go.substring(0, les1go.indexOf("]"));
			// System.out.println(lesgo);

			/**读取用户数据 */
			Map<String, Object> readUser = user.findUserByUsername(username);
			if (readUser != null) {
				if (readUser.get("password").equals(password)) {
					
					session.setAttribute(InfoData.Session_USERUID, readUser.get("uid"));
					session.setAttribute(InfoData.Session_USERNAME, readUser.get("username"));
					//如果获取url出错，就使用uid定位主页
					try {
						@SuppressWarnings("unused")
						String userurl = readUser.get("url").toString();
						session.setAttribute(InfoData.Session_USERURL, readUser.get("url")); 
					} catch (Exception e) {
						session.setAttribute(InfoData.Session_USERMETHOD, "ID");
						response.sendRedirect("/home/"+ readUser.get("uid"));
					}
					
					session.setAttribute(InfoData.Session_USERMETHOD, "URL");
					
					response.sendRedirect("/id/"+ readUser.get("url"));
					return;

				}
				response.sendRedirect("/sign_in");
			}
			response.sendRedirect("/sign_in");
		} catch (Exception e) {
			response.sendRedirect("/sign_in");
		}
		
	}

	/**
	 * Ajax进行验证用
	 */
	@RequestMapping(value = "/sign_in", method = RequestMethod.PUT)
	@ResponseBody
	public String Sign_inTest(String username, String password){

		Map<String, Object>readuser = user.findUserByUsername(username);
		if (readuser != null) {
			String Mapusername = readuser.get("username").toString();
			String Mappassword = readuser.get("password").toString();
			if (Mapusername.equals(username) && Mappassword.equals(password)) {
				//验证成功
				return "true";
			}
			return "用户名或密码不正确";
		}
		return "用户名或密码不正确";
	}
	
	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String sign_in_get(Model model) {
		return "users/sign_in";
	}
}
