package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import flashcards.Answer;
import flashcards.AnswerDao;
import flashcards.Category;
import flashcards.CategoryDao;
import flashcards.Question;
import flashcards.QuestionDao;
import flashcards.User;
import flashcards.UserDao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HomePageController implements Initializable {

	@FXML
	private Label homeLabel;
	@FXML
	private Label meddelande;
	@FXML
	private Label meddelande2;
	@FXML
	private MenuButton väljÄmne;
	@FXML
	private Button läggTillÄmne;
	@FXML
	private Button tillSpel;
	@FXML
	private Button läggTillFråga;
	@FXML
	private ToggleButton rätt1;
	@FXML
	private ToggleButton rätt2;
	@FXML
	private ToggleButton rätt3;
	@FXML
	private ToggleButton rätt4;
	@FXML
	private TextField ämneBar;
	@FXML
	private TextField frågabar;
	@FXML
	private TextField svarBar1;
	@FXML
	private TextField svarBar2;
	@FXML
	private TextField svarBar3;
	@FXML
	private TextField svarBar4;
	
	

	private CategoryDao cDao = new CategoryDao();
	private QuestionDao qDao = new QuestionDao();
	private AnswerDao aDao = new AnswerDao();
	private Question selectedQuestion;

	private ArrayList<Category> categorys;
	private Category selectedCategory;
	private ArrayList<Question> questions;

	@FXML
	private void LäggTillÄmneButton(ActionEvent event) throws IOException {
		String NyttÄmne = ämneBar.getText();

		try {

			Category ämne = new Category();
			ämne.setCategory(NyttÄmne);

			cDao.addCategory(ämne);

			Color paint = new Color(0.076, 1.0, 0.01, 1.0);
			meddelande.setTextFill(paint);
			meddelande.setText("Du har skapat ett ny " + NyttÄmne + " mapp!");
			ämneBar.clear();

		} catch (SQLException e) {
			e.printStackTrace();
			Color paint2 = new Color(1.0, 0.01, 0.01, 1.0);
			meddelande.setTextFill(paint2);
			meddelande.setText("Det här ämnet finns redan!");
			return;

		}

	}

	@FXML
	private void LäggTillFrågaButton(ActionEvent e) {
		String nyfråga = frågabar.getText();
		ArrayList<String> svar = new ArrayList<>();
		ArrayList<Boolean> rätt = new ArrayList<>();
		svar.add(svarBar1.getText().trim());
		if (rätt1.isSelected()) {
			rätt.add(true);
		} else {
			rätt.add(false);
		}
		
		svar.add(svarBar2.getText().trim());
		if (rätt2.isSelected()) {
			rätt.add(true);
		} else {
			rätt.add(false);
		}
		
		svar.add(svarBar3.getText().trim());
		if (rätt3.isSelected()) {
			rätt.add(true);
		} else {
			rätt.add(false);
		}
		
		svar.add(svarBar4.getText().trim());
		if (rätt4.isSelected()) {
			rätt.add(true);
		} else {
			rätt.add(false);
		}
		

		try {
			Question question = new Question();
			question.setQuestion(nyfråga);
			question.setCategory(selectedCategory);
			qDao.addQuestion(question);
			ArrayList<Question> frågor = qDao.getQuestions();
			for(Question q : frågor){
				if (q.getQuestion().equals(nyfråga)) {
					selectedQuestion = q;
					break;
				}
			}
			for(int i = 0; i < svar.size(); i++) {
				String s = svar.get(i);
				boolean c = rätt.get(i);
				if (!"".equals(s)) {
					Answer a = new Answer();
					a.setQuestion(selectedQuestion);
					a.setAnswer(s);
					a.setCorrect(c);
					aDao.addAnswer(a);
				}
			}
			
			

		} catch (Exception r) {
			r.printStackTrace();
			System.out.println(r);
			Color paint2 = new Color(1.0, 0.01, 0.01, 1.0);
			meddelande2.setTextFill(paint2);
			meddelande2.setText("Den frågan finns redan!");
			return;

		}
		Color paint = new Color(0.076, 1.0, 0.01, 1.0);
		meddelande2.setTextFill(paint);
		meddelande2.setText("Du har lagt till en ny fråga!");
		System.out.println("Lyckat!");

	}
	
	

	@FXML
	private void gåTillSpelAction(ActionEvent event) throws IOException {
		Parent gåSpel_page_parent = FXMLLoader.load(getClass().getResource("FlashKortPage.fxml"));
		Scene gåSpel_page_scene = new Scene(gåSpel_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(gåSpel_page_scene);
		app_stage.show();
	}

	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		CategoryDao cDao = new CategoryDao();
		categorys = cDao.getCategorys();
		for (Category c : categorys) {
			MenuItem menuItem = new MenuItem(c.getCategory());
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			        pickSelectedCategory(menuItem.getText());
			    }
			});
			väljÄmne.getItems().add(menuItem);
		}
		
		QuestionDao qDao = new QuestionDao();
		questions = qDao.getQuestions();

	}
	
	private void pickSelectedCategory(String category) {
		for (Category c : categorys) {
			if (c.getCategory().equals(category)) {
				selectedCategory = c;
				väljÄmne.setText(category);
				break;
			}
			
		}
		
		/*for(Question q : questions) {
			if (q.getCategory().equals(questions)) {
				
			}
		}*/
	}

	
	
}
