package com.grp.ln.web.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.grp.ln.web.Constant;

public class Interceptor implements HandlerInterceptor {

	public List<String> excludedUrls;
	private static final String LOGIN_URL = "/mobile/user/tologin";

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub

		String requestUri = req.getRequestURI();
		for (String url : excludedUrls) {
			if (url.contains("*")) {
				url = url.substring(0, url.lastIndexOf("*"));
				if (requestUri.lastIndexOf(url) != -1) {
					return true;
				}
			} else if (requestUri.endsWith(url)) {
				return true;
			}
		}

		HttpSession session = req.getSession(true);
		// 从session 里面获取用户名的信息
		Object obj = session.getAttribute(Constant.USER_INFO);
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (obj == null || "".equals(obj.toString())) {
			// res.sendRedirect(LOGIN_URL);
			req.getRequestDispatcher(LOGIN_URL).forward(req, res);
			return false;

		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

}
