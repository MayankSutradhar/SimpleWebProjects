package com.app.simplewebapp.conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MySQLConnUtils.getMySQLConnection();
	}

	public static Connection closeQuitly(Connection conn) throws SQLException {
		conn.close();
		return conn;
	}

	public static Connection rollbackQuitly(Connection conn) throws SQLException {
		conn.rollback();
		return conn;
	}
}
