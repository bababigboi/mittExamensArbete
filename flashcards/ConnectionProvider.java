package flashcards;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	private static Connection conn = null;

	public static Connection getConnection() throws Exception {
		if (conn != null) {
			return conn;
		}

		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/flashcards";
			String username = "root";
			String password = "Byt123!";
			Class.forName(driver);

			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");

			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
}
