package com.app.simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.simplewebapp.conn.ConnectionUtils;
import com.app.simplewebapp.conn.MySQLConnUtils;
import com.app.simplewebapp.model.Products;
import com.app.simplewebapp.utils.DBUtils;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/productList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn;
		String errorString="";
		List<Products> productList=new ArrayList<>();
		try {
			conn=MySQLConnUtils.getMySQLConnection();
			productList=DBUtils.listProducts(conn);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorString=e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorString=e.getMessage();
		}
		request.setAttribute("productList", productList);
		request.setAttribute("errorString", errorString);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/productList.jsp");
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
