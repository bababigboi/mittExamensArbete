module Prototyp_flashcards {
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires mysql.connector.j;
	
	opens application to javafx.graphics, javafx.fxml;
}
