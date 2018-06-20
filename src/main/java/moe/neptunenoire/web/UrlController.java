package moe.neptunenoire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moe.neptunenoire.web.bean.SignInBean;
import moe.neptunenoire.web.mysql.MaiKissReo;
import moe.neptunenoire.web.table.Users;

/**
 * <pre>
 * showIndex()		GET
 * sendIndex()		POST
 * putIndex()		PUT
 * deleteIndex()	DELETE
 *
 * showIndexAJ()	GET
 * sendIndexAJ()	POST
 * putIndexAJ()		PUT
 * deleteIndexAJ()	DELETE
 * </pre>
 *
 * @author M
 *
 */
@Controller
@EnableAutoConfiguration
public class UrlController {

	@Autowired
	public UrlController(MaiKissReo maiKissReo) {

	}

/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndex(Model model) {
		return "";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.GET)
	public String showSignIn(Model model) {
		return "";
	}

	@RequestMapping(value = "/sign_in", method = RequestMethod.POST)
	public String sendSignIn(SignInBean signInBean) {
		return "";
	}

	@RequestMapping(value = "/sign_up", method = RequestMethod.GET)
	public String showSignUp(Model model) {
		return "";
	}

	@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
	public String sendSignUp(Users users) {
		return "";
	}
/*
 * ==========================================================================
 * http://localhost/bangumi/						Index Root
 * ==========================================================================
 */
/*
 * ==========================================================================
 * http://localhost/id/								Index Root
 * ==========================================================================
 */
/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
/*
 * ==========================================================================
 * http://localhost/								Index Root
 * ==========================================================================
 */
}
