package main;


import java.util.Collection;
import java.util.List;

import cern.mpe.systems.core.service.provider.SystemsProvider;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A standard manager for the javafx table view
 * @author Lagache
 *
 */
public class TableController {
	
	private TableView<TableItem> table;
	private String[] tableTitle;
		

	public TableView<TableItem> getTable() {
		return table;
	}



	public void setTable(TableView<TableItem> table) {
		this.table = table;
	}

	

	public String[] getTableTitle() {
		return tableTitle;
	}



	public void setTableTitle(String[] tableTitle) {
		this.tableTitle = tableTitle;
	}



	public TableController(TableView<TableItem> table) {
		super();
		this.table = table;
	}



	/**
	 * Initialize the {@link TableView} , filling the {@link TableColumn} with a collection {@link TableItem}
	 * @param collection of {@link TableItem}
	 *  
	 */
	@FXML
    protected void initialize(Collection<TableItem> collection) {

		for(int i = 1;i<=((List<TableItem>) collection).get(0).nbProps;i++)
		{
			PropertyValueFactory<TableItem, String> columnProperty = new PropertyValueFactory<TableItem, String>("prop"+i);
			TableColumn<TableItem, String> column;
			if(tableTitle.length < i)
				column = new TableColumn<TableItem, String>("C"+i);
			else
				column = new TableColumn<TableItem, String>(tableTitle[i-1]);
			table.getColumns().add(column);
			column.setCellValueFactory( columnProperty );
		}
		table.setItems( (ObservableList<TableItem>) collection );
    }
}
