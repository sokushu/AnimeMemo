package moe.neptunenoire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import moe.neptunenoire.web.bean.SignInBean;
import moe.neptunenoire.web.mysql.MaiKissReo;

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
