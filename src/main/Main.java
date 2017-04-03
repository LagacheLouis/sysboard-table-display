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
			StackPane page = (StackPane) FXMLLoader.load(Main.class.getResource("tableView.fxml"));
			scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMaximized(true);
			
			TableView<TableItem> tab = (TableView<TableItem>)scene.lookup("#tab");
			TableController controller = new TableController(tab);
			String[] tableTitles = {"index","name","id","relations"};	
			controller.setTableTitle(tableTitles);
			controller.initialize((Collection<TableItem>) DataLayerImpl.getInstance().getData());
			
			Button btnLaunchFilters = (Button)scene.lookup("#btnLaunchFilters");
			btnLaunchFilters.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					System.out.println("click");
					tab.getItems().clear();
					try {
						tab.setItems((ObservableList<TableItem>) DataLayerImpl.getInstance().getData());
					} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
							| SecurityException e) {
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
