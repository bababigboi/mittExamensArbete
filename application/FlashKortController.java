package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import flashcards.Category;
import flashcards.CategoryDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class FlashKortController implements Initializable {

	@FXML
	private MenuButton väljÄmnen;
	@FXML
	private MenuBar menuBar;
	@FXML
	private Menu action1;
	@FXML
	private MenuItem item1;
	@FXML
	private MenuItem item2;
	@FXML
	private MenuItem item3;
	@FXML
	private Menu action2;
	@FXML
	private Menu action3;
	@FXML
	private ToolBar toolBar;
	@FXML
	private Button starta;
	@FXML
	private Button läggTillNytt;
	@FXML
	private Button loggaUt;
	@FXML
	private Label pageLabel, label2, label3, label4, label5, label6, label7, label8, label9;
	
	
	private ArrayList<Category> categorys;

	

	
	
	@FXML
	private void loggaUtAction(ActionEvent event) throws IOException {
		Parent loggUt_page_parent = FXMLLoader.load(getClass().getResource("test.fxml"));
		Scene loggUt_page_scene = new Scene(loggUt_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(loggUt_page_scene);
		app_stage.show();
	}
	@FXML
	private void läggTillNyttAction(ActionEvent event) throws IOException {
		Parent läggTill_page_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		Scene läggTill_page_scene = new Scene(läggTill_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(läggTill_page_scene);
		app_stage.show();
	}
	
	@FXML
	private void startaSpelAction(ActionEvent event) throws IOException{
		Parent startaSpel_page_parent = FXMLLoader.load(getClass().getResource("SpelPage.fxml"));
		Scene startaSpel_page_scene = new Scene(startaSpel_page_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(startaSpel_page_scene);
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
			väljÄmnen.getItems().add(menuItem);
		}
	}
	
	private void pickSelectedCategory(String category) {
		for (Category c : categorys) {
			if (c.getCategory().equals(category)) {
				Globals.setSelectedCategory(c);
				väljÄmnen.setText(category);
				break;
			}
		}
	}
	
	
	
	
}
