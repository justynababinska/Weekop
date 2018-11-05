package pl.justynababinska.weekop.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static DataSource dataSource;

	public Connection getConnection() throws SQLException {
		return getDSInstance().getConnection();
	}

	public static DataSource getDSInstance() {
		if (dataSource == null) {
			try {
				Context initialContext = new InitialContext();
				Context envContext = (Context) initialContext.lookup("java:comp/env");
				dataSource = (DataSource) envContext.lookup("jdbc/weekop");
			} catch (NamingException ex) {
				ex.printStackTrace();
			}
		}
		return dataSource;

	}
}
