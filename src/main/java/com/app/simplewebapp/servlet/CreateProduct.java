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

import com.app.simplewebapp.conn.MySQLConnUtils;
import com.app.simplewebapp.model.Products;
import com.app.simplewebapp.utils.DBUtils;
import com.app.simplewebapp.utils.MyUtils;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/createProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createProduct.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = MyUtils.getStoreConnection(request);
		String code=request.getParameter("code");
		String name=request.getParameter("name");
		String strPrice=request.getParameter("price");
		double price=0;
		try {
			price=Double.parseDouble(strPrice);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Products products=new Products(code, name, price);
		
		String errorString=null;
		String regex="[a-zA-Z0-9]\\w+";
		
		if(code==null||!code.matches(regex)) {
			errorString="Product Code Invalid!";
		}
		
		if(errorString==null) {
			try {
				DBUtils.insertProduct(conn, products);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorString=e.getMessage();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorString=e.getMessage();
			}
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("products", products);
		
		if(errorString!=null) {
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/WEB-INF/views/createProduct.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/productList");
		}
	}

}
