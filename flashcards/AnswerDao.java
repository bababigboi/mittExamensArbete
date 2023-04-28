package flashcards;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDao {
	
	public void addAnswer (Answer answer) {
		int correct = 0; 
		if (answer.isCorrect()) {
			correct = 1;
		}
		Connection conn;
		
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		try {

			PreparedStatement posted = conn
					.prepareStatement("INSERT INTO answers (question_id, answer, correct) VALUES ('" + answer.getQuestion().getId() +"', '" + answer.getAnswer() + "', " + correct + ")");
			posted.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public ArrayList<Answer> getAnswers(Question question){
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement("SELECT id, answer, correct FROM answers WHERE question_id = ?");
			statement.setInt(1, question.getId());

			ResultSet resultset = statement.executeQuery();

			ArrayList<Answer> answers = new ArrayList<Answer>();
			while (resultset.next()) {
				Answer a = new Answer();
				a.setId(resultset.getInt("id"));
				a.setAnswer(resultset.getString("answer"));
				int c = resultset.getInt("correct");
				if (c == 1) {
					a.setCorrect(true);
				} else {
					a.setCorrect(false);
				}
				a.setQuestion(question);
				answers.add(a);

			}
			return answers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
