package com.app.simplewebapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.simplewebapp.conn.ConnectionUtils;
import com.app.simplewebapp.model.Products;
import com.app.simplewebapp.model.UserAccount;

public class DBUtils {

	public static UserAccount findUser(Connection conn,String userName, String password) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlFindUser = "SELECT USER_NAME,GENDER,PASSWORD FROM user_account WHERE USER_NAME=? AND PASSWORD=?";
		PreparedStatement ps = conn.prepareStatement(sqlFindUser);
		ps.setString(1, userName);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);
			user.setGender(rs.getString("GENDER"));
			return user;
		}

		rs.close();
		ps.close();
		conn.close();
		return null;
	}

	public static UserAccount findUser(Connection conn, String userName) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlFindUser = "SELECT USER_NAME,GENDER,PASSWORD FROM user_account WHERE USER_NAME=? AND PASSWORD=?";
		PreparedStatement ps = conn.prepareStatement(sqlFindUser);
		ps.setString(1, userName);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			UserAccount user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(rs.getString("PASSWORD"));
			user.setGender(rs.getString("GENDER"));
			return user;
		}

		rs.close();
		ps.close();
		conn.close();
		return null;
	}

	public static List<Products> listProducts(Connection conn) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlListProducts = "SELECT CODE,NAME,PRICE FROM PRODUCT";
		PreparedStatement ps = conn.prepareStatement(sqlListProducts);
		ResultSet rs = ps.executeQuery();
		List<Products> list = new ArrayList<>();
		while (rs.next()) {
			Products product = new Products();
			product.setCode(rs.getString("CODE"));
			product.setName(rs.getString("NAME"));
			product.setPrice(rs.getDouble("PRICE"));
			list.add(product);
		}

		rs.close();
		ps.close();
		conn.close();
		return list;
	}

	public static Products findProduct(Connection conn,String code) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlFindProduct = "SELECT CODE,NAME,PRICE FROM PRODUCT WHERE CODE=?";
		PreparedStatement ps = conn.prepareStatement(sqlFindProduct);
		ps.setString(1, code);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Products product = new Products();
			product.setCode(code);
			product.setName(rs.getString("NAME"));
			product.setPrice(rs.getDouble("PRICE"));
			return product;
		}

		rs.close();
		ps.close();
		conn.close();
		return null;
	}

	public static void insertProduct(Connection conn,Products product) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlInsertProduct = "INSERT INTO PRODUCT(CODE,NAME,PRICE) values(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlInsertProduct);
		ps.setString(1, product.getCode());
		ps.setString(2, product.getName());
		ps.setDouble(3, product.getPrice());

		ps.executeUpdate();
		ps.close();
		conn.close();
	}
	
	public static void updateProduct(Connection conn,Products product) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlUpdateProduct = "UPDATE PRODUCT SET NAME=?,PRICE=? WHERE CODE=?";
		PreparedStatement ps = conn.prepareStatement(sqlUpdateProduct);
		
		ps.setString(1, product.getName());
		ps.setDouble(2, product.getPrice());
		ps.setString(3, product.getCode());
		
		ps.executeUpdate();
		ps.close();
		conn.close();
	}

	public static void deleteProduct(Connection conn,String code) throws ClassNotFoundException, SQLException {
		conn = ConnectionUtils.getConnection();
		String sqlDeleteProduct = "DELETE FROM PRODUCT WHERE CODE=?";
		PreparedStatement ps = conn.prepareStatement(sqlDeleteProduct);
		ps.setString(1, code);

		ps.executeUpdate();

		ps.close();
		conn.close();
	}
}
