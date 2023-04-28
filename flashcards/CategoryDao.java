package flashcards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
	
	public void addCategory(Category category) throws SQLException{
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO categorys (category) VALUES ('" + category.getCategory() + "')");
		stmt.executeUpdate();
	}
	
	public ArrayList<Category> getCategorys() {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		PreparedStatement stmt;
		ArrayList<Category> categorys = new ArrayList<>();
		try {
			stmt = conn.prepareStatement("SELECT id, category FROM categorys");
			ResultSet resultset = stmt.executeQuery();
			
			while (resultset.next()) {
				Category c = new Category();
				c.setId(resultset.getInt("id"));
				c.setCategory(resultset.getString("category"));
				categorys.add(c);
			}
			return categorys;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
			
		}
		
		
	}

}
