package library.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbsBaseRepository {
	public Connection conn;
	static final String USER = "sa";
	static final String PASS = "sa";

	public AbsBaseRepository() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://localhost;databasename=Library;username=" + USER + ";password=" + PASS);
		} catch (Exception e) {
			conn();
		}
	}

	public void conn() {
		try {
			Connection conn1 = DriverManager.getConnection(
					"jdbc:sqlserver://AnhHuy\\SQLEXPRESS;databasename=master;username=" + USER + ";password=" + PASS);
			if (conn1 != null) {
				conn1.createStatement().executeUpdate("CREATE DATABASE [Library]");
				conn = DriverManager.getConnection(
						"jdbc:sqlserver://AnhHuy\\SQLEXPRESS;databasename=Library;username=" + USER + ";password=" + PASS);
				conn.createStatement().executeUpdate(readerFileSQL("lib.txt"));
				conn.createStatement().executeUpdate(readerFileSQL("view.txt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readerFileSQL(String url) {
		@SuppressWarnings("resource")
		FileInputStream fin = null;
		String sql = "";
		try {
			fin = new FileInputStream(url);
			int ch;
			while ((ch = fin.read()) != -1)
				sql += (char) ch;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null)
					fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sql;
	}

	public AbsBaseRepository(Connection conn) {
		this.conn = conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}