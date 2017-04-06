package filters;

import main.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * An example of an hand made filterView
 * @author Lagache
 *
 */
public class FilterTestView implements FilterView {
	
	private static TextField field;
	
	@Override
	public void initialize() {
		VBox box = (VBox)Main.scene.lookup("#customFilterVBox");
		Label label = new Label("Identifiant");
		field = new TextField();
		box.getChildren().add(label);
		box.getChildren().add(field);

	}

	@Override
	public Object getValueFromView() {
		String val = field.getText().toLowerCase();
		return val;
	}

}
