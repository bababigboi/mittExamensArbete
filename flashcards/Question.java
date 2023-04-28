package flashcards;

import java.util.ArrayList;

public class Question {
	private int id;
	private String question;
	private ArrayList<Answer> answers = new ArrayList<>();
	private Category category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public ArrayList<Answer> getCorrectAnswers() {
		ArrayList<Answer> correctAnswers = new ArrayList<>();
		for (Answer answer : answers) {
			if (answer.isCorrect()) {
				correctAnswers.add(answer);
			}
		}
		return correctAnswers;
	}

}
