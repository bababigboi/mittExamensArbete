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
	private MenuButton v�lj�mne;
	@FXML
	private Button l�ggTill�mne;
	@FXML
	private Button tillSpel;
	@FXML
	private Button l�ggTillFr�ga;
	@FXML
	private ToggleButton r�tt1;
	@FXML
	private ToggleButton r�tt2;
	@FXML
	private ToggleButton r�tt3;
	@FXML
	private ToggleButton r�tt4;
	@FXML
	private TextField �mneBar;
	@FXML
	private TextField fr�gabar;
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
	private void L�ggTill�mneButton(ActionEvent event) throws IOException {
		String Nytt�mne = �mneBar.getText();

		try {

			Category �mne = new Category();
			�mne.setCategory(Nytt�mne);

			cDao.addCategory(�mne);

			Color paint = new Color(0.076, 1.0, 0.01, 1.0);
			meddelande.setTextFill(paint);
			meddelande.setText("Du har skapat ett ny " + Nytt�mne + " mapp!");
			�mneBar.clear();

		} catch (SQLException e) {
			e.printStackTrace();
			Color paint2 = new Color(1.0, 0.01, 0.01, 1.0);
			meddelande.setTextFill(paint2);
			meddelande.setText("Det h�r �mnet finns redan!");
			return;

		}

	}

	@FXML
	private void L�ggTillFr�gaButton(ActionEvent e) {
		String nyfr�ga = fr�gabar.getText();
		ArrayList<String> svar = new ArrayList<>();
		ArrayList<Boolean> r�tt = new ArrayList<>();
		svar.add(svarBar1.getText().trim());
		if (r�tt1.isSelected()) {
			r�tt.add(true);
		} else {
			r�tt.add(false);
		}
		
		svar.add(svarBar2.getText().trim());
		if (r�tt2.isSelected()) {
			r�tt.add(true);
		} else {
			r�tt.add(false);
		}
		
		svar.add(svarBar3.getText().trim());
		if (r�tt3.isSelected()) {
			r�tt.add(true);
		} else {
			r�tt.add(false);
		}
		
		svar.add(svarBar4.getText().trim());
		if (r�tt4.isSelected()) {
			r�tt.add(true);
		} else {
			r�tt.add(false);
		}
		

		try {
			Question question = new Question();
			question.setQuestion(nyfr�ga);
			question.setCategory(selectedCategory);
			qDao.addQuestion(question);
			ArrayList<Question> fr�gor = qDao.getQuestions();
			for(Question q : fr�gor){
				if (q.getQuestion().equals(nyfr�ga)) {
					selectedQuestion = q;
					break;
				}
			}
			for(int i = 0; i < svar.size(); i++) {
				String s = svar.get(i);
				boolean c = r�tt.get(i);
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
			meddelande2.setText("Den fr�gan finns redan!");
			return;

		}
		Color paint = new Color(0.076, 1.0, 0.01, 1.0);
		meddelande2.setTextFill(paint);
		meddelande2.setText("Du har lagt till en ny fr�ga!");
		System.out.println("Lyckat!");

	}
	
	

	@FXML
	private void g�TillSpelAction(ActionEvent event) throws IOException {
		Parent g�Spel_page_parent = FXMLLoader.load(getClass().getResource("FlashKortPage.fxml"));
		Scene g�Spel_page_scene = new Scene(g�Spel_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(g�Spel_page_scene);
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
			v�lj�mne.getItems().add(menuItem);
		}
		
		QuestionDao qDao = new QuestionDao();
		questions = qDao.getQuestions();

	}
	
	private void pickSelectedCategory(String category) {
		for (Category c : categorys) {
			if (c.getCategory().equals(category)) {
				selectedCategory = c;
				v�lj�mne.setText(category);
				break;
			}
			
		}
		
		/*for(Question q : questions) {
			if (q.getCategory().equals(questions)) {
				
			}
		}*/
	}

	
	
}
