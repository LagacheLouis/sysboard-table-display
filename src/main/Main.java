package main;
	
import java.io.IOException;
import java.util.Collection;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	public static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		    FXMLLoader fxmlLoader = new FXMLLoader();
			StackPane page = (StackPane) fxmlLoader.load(Main.class.getResourceAsStream("tableView.fxml"));
			scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMaximized(true);
			System.out.println(fxmlLoader.getController().toString());
			((TableController)fxmlLoader.getController()).buildTable();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
