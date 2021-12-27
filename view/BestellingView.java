package view;

import controller.BestelViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Bestellijn;
import view.bestelling.BestellingPane;

public class BestellingView {
	private Stage stage = new Stage();
	private BestellingPane bestellingPane;
	private BestelViewController controller;
	private Alert alert;
		
	public BestellingView() {
		stage.setTitle("BESTELLING VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 650);
		this.bestellingPane = new BestellingPane();
		this.bestellingPane.prefHeightProperty().bind(scene.heightProperty());
		this.bestellingPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(this.bestellingPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public void setController(BestelViewController controller) {
		this.controller = controller;
		this.bestellingPane.setController(controller);
	}

	public Bestellijn getSelectedBestellijn() {
		return this.bestellingPane.getSelectedBestellijn();
	}

	public void reset() { this.bestellingPane.reset(); }

	public void refresh() { this.bestellingPane.refresh(); }

	public void showError(String msg) {
		this.alert = new Alert(Alert.AlertType.NONE, msg, ButtonType.OK);
		this.alert.show();
	}
}
