package view;

import controller.KitchenViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.kitchen.KitchenPane;

public class KitchenView {
	private Stage stage = new Stage();
	private KitchenPane kitchenPane;
	
	public KitchenView(){			
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 200);
		this.kitchenPane = new KitchenPane();
		this.kitchenPane.prefHeightProperty().bind(scene.heightProperty());
		this.kitchenPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(this.kitchenPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setController(KitchenViewController controller) {
		this.kitchenPane.setController(controller);
	}

	public void refresh() { this.kitchenPane.refresh(); }
}
