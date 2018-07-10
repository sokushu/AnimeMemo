package moe.neptunenoire.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import moe.neptunenoire.web.controller.error.BangumiNotFoundException;
import moe.neptunenoire.web.controller.error.BlogNotFoundException;
import moe.neptunenoire.web.controller.error.HomeNotFoundException;
import moe.neptunenoire.web.controller.error.UserNotSignInException;

/**
 * 错误处理控制
 * @author jo
 *
 */
@ControllerAdvice
public class ErrorCatch {

	/**
	 * 未找到用户，或未找到主页
	 * @param e
	 * @param model
	 * @param session
	 * @return
	 */
	@ExceptionHandler(value = HomeNotFoundException.class)
	public String homeNotFoundError(HomeNotFoundException e, Model model, HttpSession session) {
		model.addAttribute("test", "HomeNotFoundException");
		return "test";
	}
	
	/**
	 * 未找到动画数据
	 * @param e
	 * @param model
	 * @param session
	 * @return
	 */
	@ExceptionHandler(value = BangumiNotFoundException.class)
	public String bangumiNotFoundError(BangumiNotFoundException e, Model model, HttpSession session) {
		return "";
	}
	
	/**
	 * 
	 * @param e
	 * @param model
	 * @param session
	 * @return
	 */
	@ExceptionHandler(value = BlogNotFoundException.class)
	public String blogNotFoundError(BlogNotFoundException e, Model model, HttpSession session) {
		return "";
	}
	
	/**
	 * 用户未登录
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = UserNotSignInException.class)
	public String userNotSignInError(UserNotSignInException e) {
		// 跳转到登录页面
		return "redirect:/sign_in";
	}

}
