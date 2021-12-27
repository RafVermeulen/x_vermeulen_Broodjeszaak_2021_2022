package view;

import controller.BroodjesOverviewController;
import controller.SettingsController;
import controller.StatistiekController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.admin.AdminMainPane;

public class AdminView {
	private Stage stage = new Stage();
	private AdminMainPane adminMainPane;
		
	public AdminView() {
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(20);

		Group root = new Group();
		Scene scene = new Scene(root, 650, 400);
		this.adminMainPane = new AdminMainPane();
		this.adminMainPane.prefHeightProperty().bind(scene.heightProperty());
		this.adminMainPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(this.adminMainPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setOverviewController(BroodjesOverviewController controller) {
		this.adminMainPane.setOverviewController(controller);
	}

	public void setStatistiekController(StatistiekController controller) {
		this.adminMainPane.setStatistiekController(controller);
	}

	public void setSettingsController(SettingsController controller) {
		this.adminMainPane.setSettingsController(controller);
	}
}
