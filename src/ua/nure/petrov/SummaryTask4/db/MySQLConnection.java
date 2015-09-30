package ua.nure.petrov.SummaryTask4.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class MySQLConnection {

	private static String url = "jdbc:mysql://localhost:3306/provider";
	private static String username = "root";
	private static String password = "Vlad1kpetrov";

	private static final Logger LOG = Logger.getLogger(MySQLConnection.class);
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
	}

	public static Connection getConnection() {
		Connection con = null;
		if (con == null) {
			try {
				con = DriverManager.getConnection(url, username, password);
				con.setAutoCommit(false);
			} catch (Exception e) {
				LOG.error(e);
				return null;
			}
		}
		return con;
	}

	public static Connection getWebConnection() {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/provider");
			con = ds.getConnection();
		} catch (SQLException | NamingException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}
