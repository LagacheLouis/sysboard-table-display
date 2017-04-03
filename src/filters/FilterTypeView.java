package filters;

import main.DataLayerImpl;
import main.Main;
import javafx.scene.control.ComboBox;

public class FilterTypeView implements FilterView {
	
	private static ComboBox cbType;
	
	@Override
	public void initialize() {
		
		cbType = (ComboBox) Main.scene.lookup("#cbType");
		cbType.getItems().add("-");
		cbType.getItems().addAll(DataLayerImpl.getInstance().getSystemsManager().getAllSystemTypes());
		cbType.getSelectionModel().selectFirst();
	}

	@Override
	public Object getValueFromView() {
		
		String value = cbType.getValue().toString();
		return value;
	}

}
