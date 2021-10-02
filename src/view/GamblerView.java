package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.*;

/**
 * @author niet van toepassing, gegeven
 */
public class GamblerView {
	private Stage stage = new Stage();
	private GamblerViewPane gamblerViewPane = new GamblerViewPane();

	public void setGamblerViewPane(GamblerViewPane gamblerViewPane) {
		this.gamblerViewPane = gamblerViewPane;
	}

	public GamblerViewPane getGamblerViewPane() {
		return gamblerViewPane;
	}

	public GamblerView(){
		stage.setTitle("GAMBLER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);




		root.getChildren().add(gamblerViewPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
