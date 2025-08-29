package com.app.simplewebapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.simplewebapp.model.UserAccount;
import com.app.simplewebapp.utils.MyUtils;

/**
 * Servlet implementation class UserInfoServlet
 */
@WebServlet("/userinfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		UserAccount loginedUser=MyUtils.getLoginedUser(session);
		if(loginedUser==null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setAttribute("user", loginedUser);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoViews.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
