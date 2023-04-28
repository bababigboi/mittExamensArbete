package flashcards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDao {
	public AnswerDao answerDao = new AnswerDao();

	public void addQuestion(Question question) throws Exception {
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement posted = conn.prepareStatement("INSERT INTO questions (category_id, question) VALUES (" + question.getCategory().getId() + ", '" + question.getQuestion() + "')");
		posted.executeUpdate();

		
	}

	public ArrayList<Question> getQuestions() {

		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		PreparedStatement statement;
		try {
			String sql = "SELECT id, question "; 
			sql += "FROM questions";
			
			
			statement = conn.prepareStatement(sql);

			ResultSet resultset = statement.executeQuery();

			System.out.println(resultset);
			ArrayList<Question> questions = new ArrayList<Question>();
			while (resultset.next()) {
				Question q = new Question();
				q.setId(resultset.getInt("id"));
				q.setQuestion(resultset.getString("question"));
				
				q.setAnswers(answerDao.getAnswers(q));
				
				questions.add(q);

			}
			System.out.println(questions);
			return questions;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		
		
			
		
		
	}
	
	public ArrayList<Question> getQuestionsByCategory(Category category) {

		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		PreparedStatement statement;
		try {
			String sql = "SELECT id, question "; 
			sql += "FROM questions ";
			sql += "WHERE category_id = " + category.getId();
			
			System.out.println(sql);
			statement = conn.prepareStatement(sql);

			ResultSet resultset = statement.executeQuery();

			System.out.println(resultset);
			ArrayList<Question> questions = new ArrayList<Question>();
			while (resultset.next()) {
				Question q = new Question();
				q.setId(resultset.getInt("id"));
				q.setQuestion(resultset.getString("question"));
				
				q.setAnswers(answerDao.getAnswers(q));
				
				questions.add(q);

			}
			System.out.println(questions);
			return questions;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		
		
			
		
		
	}
	
	
	
	/*public Question getQuestion(String question) {
		
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}*/
	
	public void updateQuestion(Question question) {
		
		PreparedStatement stmt;
		Connection conn;
		
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
		try { 
			String sql = "UPDATE questions "; 
			sql += "SET question = '" + question.getQuestion() + "', ";
			sql += "category_id = " + question.getCategory().getId() + " ";
			sql += "WHERE id = " + question.getId();
			
			stmt = conn.prepareStatement(sql);
			
			stmt.executeUpdate();
			
		}catch(Exception e) {
			
		}
	
		
	}

}
