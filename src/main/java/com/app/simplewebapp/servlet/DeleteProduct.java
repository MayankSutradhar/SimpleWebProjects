package com.app.simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.simplewebapp.model.Products;
import com.app.simplewebapp.utils.DBUtils;
import com.app.simplewebapp.utils.MyUtils;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/deleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoreConnection(request);
		String code = request.getParameter("code");
		String errorString = null;

		try {
			DBUtils.deleteProduct(conn, code);
		} catch (Exception e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (errorString != null) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/productList");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
