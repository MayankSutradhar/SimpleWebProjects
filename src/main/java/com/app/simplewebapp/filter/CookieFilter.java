package com.app.simplewebapp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.app.simplewebapp.conn.ConnectionUtils;
import com.app.simplewebapp.model.UserAccount;
import com.app.simplewebapp.utils.DBUtils;
import com.app.simplewebapp.utils.MyUtils;

@WebFilter(filterName = "cookieFilter",urlPatterns = {"/*"})
public class CookieFilter implements Filter {

	public CookieFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		UserAccount userInSession = MyUtils.getLoginedUser(session);
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		Connection conn = MyUtils.getStoreConnection(request);

		String checked = (String) session.getAttribute("COOKIE_CHECKED");

		if (checked == null && conn != null) {
			String userName = MyUtils.getUserNameInCookie(req);
			try {
				UserAccount findUserName = DBUtils.findUser(conn, userName);
				MyUtils.storedLoginedUser(session, findUserName);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}
		chain.doFilter(request, response);
	}

}
