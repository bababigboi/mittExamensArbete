package application;
	
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import flashcards.Question;
import flashcards.QuestionDao;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	


	
	@Override
	public void start(Stage primaryStage) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("test.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
	/*window = primaryStage;
		
		Label rubrik = new Label("Här kan du logga in!");
		Button knapp1 = new Button("Logga in");
		knapp1.setOnAction(e -> {
			window.setScene(scene2);
			window.setFullScreen(true);
		}); 
		
		
		StackPane layout1 = new StackPane();
		layout1.getChildren().addAll(rubrik,knapp1);
		scene1 = new Scene(layout1,500, 500);
		
		knapp2 = new Button("Finns inte mycket här. Klicka på mig för att gå tillbaka!");
		knapp2.setOnAction(this);
		
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(knapp2);
		scene2 = new Scene(layout2, 500,500);
		
		window.setScene(scene1);
		window.setTitle("Flashcards");
		window.setFullScreen(true);
		window.show();*/
		
		
		
	}
	
	/*@Override
	public void handle(ActionEvent event)  {
		if(event.getSource()==knapp2) {
			String inputFråga = JOptionPane.showInputDialog("Skriv in en fråga!");
			QuestionDao qDao = new QuestionDao();
			qDao.addQuestion(inputFråga);
			ArrayList<Question> frågor = qDao.getQuestions();
			for (Question q : frågor) {
				System.out.println(q.getQuestion());
			}
			
		}
		
	}*/



	
	
}
