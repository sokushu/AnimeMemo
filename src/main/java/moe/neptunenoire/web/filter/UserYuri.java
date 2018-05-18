package moe.neptunenoire.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import moe.neptunenoire.InfoData;

/**
 * 用户权限管理<br/>
 * 用于过滤不同权限的用户
 */

public class UserYuri implements HandlerInterceptor {
//>>>>>>>>>>在请求处理之前进行调用（Controller方法调用之前）
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 得到Session
		HttpSession session = request.getSession();
		if (session != null) {
			// 返回该用户的 权限下是否可以访问该网页
			if (!InfoData.goFilter((String)(session.getAttribute(InfoData.Session_USERYURI)), request.getRequestURI())) {
				response.sendRedirect("/");
				return false;
			}
		}
		
		return true;
	}
//>>>>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 如果Model不是空的话
		if (modelAndView != null) {
			


		}
	}
//>>>>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

    
}