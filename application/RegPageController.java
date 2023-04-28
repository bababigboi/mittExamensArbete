package application;

import java.io.IOException;
import java.sql.SQLException;

import flashcards.User;
import flashcards.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegPageController {

	@FXML
	private Button skapaKonto;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Button startsida;
	
	UserDao cDao = new UserDao();
	
	
	@FXML
	private void SkapaKontoButtonAction(ActionEvent event) throws IOException{
		String användare = username.getText();
		String lösenord = password.getText();
		try {
			User user = new User();
			user.setUsername(användare);
			user.setPassword(lösenord);
			
			cDao.addUser(user);
			
			Color paint = new Color(0.076, 1.0, 0.01, 1.0);
			errorLabel.setTextFill(paint);
			errorLabel.setText("Ditt Konto har skapats!");
			
		}catch(SQLException e) {
			
		 Color paint2 = new Color(1.0, 0.01, 0.01, 1.0);
		 errorLabel.setTextFill(paint2);
		 errorLabel.setText("Det användarnamnet finns redan!");
		
		 
		}
		
	}
	
	@FXML
	private void StartSidaButtonAction(ActionEvent event) throws IOException {
		Parent loggIn_page_parent = FXMLLoader.load(getClass().getResource("test.fxml"));
		Scene loggIn_page_scene = new Scene(loggIn_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(loggIn_page_scene);
		app_stage.show();
	}
}
