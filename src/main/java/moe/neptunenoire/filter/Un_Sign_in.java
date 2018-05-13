package moe.neptunenoire.filter;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import moe.InfoData;

/**
 * 用户未登录使用的类
 */

public class Un_Sign_in implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
				// 能够找到model的情况（当输入错误网址的时候会为Null）
				if (modelAndView != null) {
					Map<String, Object> a = modelAndView.getModel();
					if (a != null) {
						a.put("issign_in", true);
						modelAndView.addAllObjects(a);
					}
				}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean isFilter = InfoData.goFilter(request.getRequestURI());
		// 如果访问网址在过滤网址列中
		if (isFilter == true) {
			HttpSession session = request.getSession();

			// 试着从session中寻找UID
			if (session.getAttribute(InfoData.Session_USERUID) == null) {
				return false;
			}else{
				return true;
			}
		}
		return true;
	}
}