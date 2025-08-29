package com.app.simplewebapp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.app.simplewebapp.conn.ConnectionUtils;
import com.app.simplewebapp.utils.MyUtils;

@WebFilter(filterName = "jdbcFilter",urlPatterns = {"/*"})
public class JDBCFilter implements Filter {

	public JDBCFilter() {
		//TODO Auto-generated constructor stub
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
	
	private boolean needJDBC(HttpServletRequest request) {
		System.out.println("JDBC Filter");
		
		String servletPath=request.getServletPath();
		String pathInfo=request.getPathInfo();
		String urlPattern=servletPath;
		
		if(pathInfo != null) {
			urlPattern=servletPath+"/*";
		}
		
		Map<String,?extends ServletRegistration> servletRegistration=request.getServletContext().getServletRegistrations();
		
		Collection<?extends ServletRegistration> values=servletRegistration.values();
		
		for(ServletRegistration sr:values) {
			Collection<String> mappings=sr.getMappings();
			if(mappings.contains(urlPattern)) {
				return true;
			}
		}
		
		return false;

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		
		if(this.needJDBC(req)) {
			System.out.println("Open connection for:"+req.getServletPath());
			Connection conn = null;
			try {
				conn=ConnectionUtils.getConnection();
				conn.setAutoCommit(false);
				
				MyUtils.storeConnection(request, conn);
				chain.doFilter(request, response);
				conn.commit();
	
			} catch (Exception e) {
				e.printStackTrace();
				try {
					ConnectionUtils.rollbackQuitly(conn);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw new ServletException();
			
			}finally {
				try {
					ConnectionUtils.closeQuitly(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			chain.doFilter(request, response);
		}
		
	}

}
