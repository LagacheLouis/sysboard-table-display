package main;


import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A standard manager for the javafx table view
 * @author Lagache
 *
 */
public class TableController implements Initializable{
	
    @FXML
	private TableView<TableItem> table;
    @FXML
    private Button btnLaunchFilters;

	/**
	 * Initialize the {@link TableView} , filling the {@link TableColumn} with a collection {@link TableItem}
	 * @param collection of {@link TableItem}
	 *  
	 */
	

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnLaunchFilters.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                System.out.println("click");
                table.getItems().clear();
                table.setItems((ObservableList<TableItem>) DataLayerImpl.getInstance().getData());
            }  
        });
    }
    public void buildTable() {
        String[] tableTitles = {"index","name","id","relations"};
        ObservableList<TableItem> collection = (ObservableList<TableItem>) DataLayerImpl.getInstance().getData();
        for(int i = 1;i<=((List<TableItem>) collection).get(0).nbProps;i++)
        {
            PropertyValueFactory<TableItem, String> columnProperty = new PropertyValueFactory<TableItem, String>("prop"+i);
            TableColumn<TableItem, String> column;
            if(tableTitles.length < i)
                column = new TableColumn<TableItem, String>("C"+i);
            else
                column = new TableColumn<TableItem, String>(tableTitles[i-1]);
            table.getColumns().add(column);
            column.setCellValueFactory( columnProperty );
        }
        
        table.setItems( (ObservableList<TableItem>) collection );
    }
}
