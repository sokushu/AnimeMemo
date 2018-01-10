package xyz.server.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户未登录使用的类
 */
public class Un_Sign_in implements HandlerInterceptor {

	//>>>>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//>>>>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//>>>>>>>>>>在请求处理之前进行调用（Controller方法调用之前）
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		try {
			/**
			 * 尝试获取Session数据
			 */
			req.getSession().getAttribute("USERUID").toString();
			req.getSession().getAttribute("USERNAME").toString();
			req.getSession().getAttribute("USERURL").toString();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			res.sendRedirect("/sign_in");
			return false;
		}
	}
}