package com.app.simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.simplewebapp.model.UserAccount;
import com.app.simplewebapp.utils.DBUtils;
import com.app.simplewebapp.utils.MyUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);

		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username & password";
		} else {
			Connection conn = MyUtils.getStoreConnection(request);
			try {
				user = DBUtils.findUser(conn, userName, password);
				if (user == null) {
					hasError = true;
					errorString = "User Name or Password Invalid";
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		if (hasError) {
			user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);

			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			rd.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			MyUtils.storedLoginedUser(session, user);
			if (remember) {
				MyUtils.storeUserCookie(response, user);
			} else {
				MyUtils.deleteUserCookie(response);
			}
			response.sendRedirect(request.getContextPath() + "/userinfo");
		}

	}

}
