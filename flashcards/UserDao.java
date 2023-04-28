package flashcards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

	
	public void addUser(User user) throws SQLException {
		
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (Username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')");
		stmt.executeUpdate();

		
	}
	
	public ArrayList<User> getUsers() {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT Username, password FROM users");
			ResultSet resultset = stmt.executeQuery();
			
			ArrayList<User> username = new ArrayList<User>();
			while (resultset.next()) {
				User u = new User();
				u.setPassword(resultset.getString("Password"));
				u.setUsername(resultset.getString("Username"));
				username.add(u);

			}
			return username;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public User getUser(String username, String password) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2,  password);
			ResultSet resultset = stmt.executeQuery();
			
			if (resultset.next()) {
				User u = new User();
				u.setPassword(resultset.getString("password"));
				u.setUsername(resultset.getString("username"));
				return u;

			}
			return null;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*public void setPassword(User user, User password) {
		  Connection conn;
			
			try {
				conn = ConnectionProvider.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			try {
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (Password) VALUES ('" + question.getId() +"', '" + answer + "')");
				
			}catch(Exception e) {
				
			}
	}*/
	
}
