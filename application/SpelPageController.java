package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import flashcards.Answer;
import flashcards.Category;
import flashcards.CategoryDao;
import flashcards.Question;
import flashcards.QuestionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SpelPageController implements Initializable {

	@FXML
	private Label lblFrågaNummer, antalRätt;
	@FXML
	private TextArea frågorDisplay;
	@FXML
	private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4, btnGåTillbaka;

	private ArrayList<Question> questions;
	private ArrayList<Question> allquestions;
	
	int currentQuestion = 0;
	int antalRättaSvar = 0;
	

	@FXML
	private void gåTillbakaAction(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("FlashKortPage.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(home_page_scene);
		app_stage.show();
	
	}

	public void loadQuestion(Question question) {
		frågorDisplay.setText(question.getQuestion());
		ArrayList<Answer> svarsVal = question.getAnswers();
		Collections.shuffle(svarsVal);

		List<Button> valButtons = new ArrayList<>();
		valButtons.add(btnAnswer1);
		valButtons.add(btnAnswer2);
		valButtons.add(btnAnswer3);
		valButtons.add(btnAnswer4);

		for (int i = 0; i < valButtons.size(); i++) {
			valButtons.get(i).setText(svarsVal.get(i).getAnswer());
		}

		for (Button button : valButtons) {
			button.setOnAction(actionEvent -> {
				for (Answer answer : question.getAnswers()) {
					if (button.getText().equals(answer.getAnswer())) {
						if (answer.isCorrect()) {
							antalRättaSvar++;
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Answer Alert");
							alert.setHeaderText("Rätt Svar!");
							alert.setContentText("Det var det rätta svaret!");
							alert.showAndWait();
						} else {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setTitle("Answer Alert");
							alert.setHeaderText("Fel Svar!");
							alert.setContentText(
									"Det var fel svar!\nDet rätta svaret är " + question.getCorrectAnswers().get(0).getAnswer());
							alert.showAndWait();
						}
					}
				}
			
				currentQuestion++;
				if (currentQuestion < questions.size()) {
					lblFrågaNummer.setText("Fråga " + (currentQuestion + 1) + " av " + questions.size());
					loadQuestion(questions.get(currentQuestion));
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Game Over Alert");
					alert.setHeaderText("Slut på Frågor!");
					alert.setContentText("Finns inga fler frågor!\nGå tillbaka till start!");
					alert.showAndWait();
					
					antalRätt.setText(antalRättaSvar + " Rätt av " + questions.size()) ;
				}
			});
		}
	}

	/*
	 * for(Button button: valButtons) { button.setOnAction(actionEvent -> { for
	 * (Answer answer : question.getAnswers()) { if
	 * (button.getText().equals(answer.getAnswer())) { if (answer.isCorrect()) {
	 * Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 * alert.setTitle("Answer Alert"); alert.setHeaderText("Correct!");
	 * alert.setContentText("You got the answer right!"); alert.showAndWait(); }
	 * else { Alert alert = new Alert(Alert.AlertType.INFORMATION);
	 * alert.setTitle("Answer Alert"); alert.setHeaderText("Incorrect!");
	 * alert.setContentText("You got the answer wrong!\nThe correct answer is " +
	 * question.getCorrectAnswers()); alert.showAndWait(); } } break; } }); }
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Hämtar alla frågor;
		QuestionDao qDao = new QuestionDao();
		questions = qDao.getQuestionsByCategory(Globals.getSelectedCategory());
		
		System.out.println(questions);
		// Blandar alla frågor.
		Collections.shuffle(questions);
		// visar första frågan
		lblFrågaNummer.setText("Fråga 1 av " + questions.size());
		if (questions.size() > 0) {
			loadQuestion(questions.get(currentQuestion));
		}

	}

}
