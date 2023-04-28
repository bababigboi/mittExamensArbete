package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import flashcards.User;
import flashcards.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller implements Initializable {
	@FXML
	private Label ourLabel;
	@FXML
	private Label invalid_label;
	@FXML
	private Button loggIn;
	@FXML
	private Button ingetKonto;
	@FXML
	private TextField username_box;

	@FXML
	private TextField password_box;

	

	
	

	@FXML
	private void LoggaInButtonAction(ActionEvent event) throws IOException {
		Parent home_page_parent = FXMLLoader.load(getClass().getResource("FlashKortPage.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		if (LoggaIn() == true) {

			app_stage.setScene(home_page_scene);
			app_stage.show();
		} else if (LoggaIn() == false) {
			username_box.clear();
			password_box.clear();
			invalid_label.setText("Tyvärr, du inget inlogg!");
		}
	}

	@FXML
	private void SkapaSidaButtonAction(ActionEvent event) throws IOException {
		Parent registrera_page_parent = FXMLLoader.load(getClass().getResource("RegPage.fxml"));
		Scene registrera_page_scene = new Scene(registrera_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(registrera_page_scene);
		app_stage.show();
	}

	@FXML
	private boolean LoggaIn() {
		String användarnamn = username_box.getText();
		String lösenord = password_box.getText();

		try {
			UserDao iDao = new UserDao();
			User user = iDao.getUser(användarnamn, lösenord);
			if (user == null) {
				return false;
			}
			String un = user.getUsername();
			String pw = user.getPassword();
			System.out.println(un);
			System.out.println(pw);
			
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}

	}
	/*
	 * @FXML private boolean KontrolleraInlogg() {
	 * 
	 * boolean let_in = false;
	 * System.out.println("SELECT FROM users WHERE Username= " + "'" +
	 * username_box.getText() + "'" + " AND Password= " + "'" +
	 * password_box.getText() + "'" );
	 * 
	 * 
	 * Connection conn = null; PreparedStatement stmt; try { UserDao uDao = new
	 * UserDao(); ArrayList<String> kontrollera = new ArrayList<String>(uDao); for
	 * (User u : kontrollera) { u.getUsername(); u.getPassword(); }
	 * 
	 * stmt = conn.prepareStatement("SELECT FROM users WHERE Username=" + "'" +
	 * username_box.getText() + "'" + " AND Password=" + "'" +
	 * password_box.getText() + "'");
	 * 
	 * ResultSet check = stmt.executeQuery();
	 * 
	 * while (check.next() ) { if (check.getString("Username") != null &&
	 * check.getString("Password") != null) { String username =
	 * check.getString("Username"); System.out.println( "Username = " + username);
	 * String password = check.getString("Password"); System.out.println(
	 * "Password = " + password); let_in = true; } } check.close(); stmt.close();
	 * conn.close();
	 * 
	 * }catch(Exception e) { System.err.println( e.getClass().getName() + ": " +
	 * e.getMessage()); System.exit(0); }
	 * System.out.println("Uppgift gjord fullbordad!"); return let_in;
	 * 
	 * }
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
