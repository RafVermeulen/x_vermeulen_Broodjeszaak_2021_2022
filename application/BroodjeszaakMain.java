package application;
	
import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.BestelFacade;
import view.AdminView;
import view.KitchenView;
import view.BestellingView;

public class BroodjeszaakMain extends Application {
	private BestelFacade bestelFacade;
	@Override
	public void start(Stage primaryStage) {
		this.bestelFacade = new BestelFacade();
		BroodjesOverviewController broodjesOverviewController = new BroodjesOverviewController(this.bestelFacade);
		StatistiekController statistiekController = new StatistiekController(this.bestelFacade);
		SettingsController settingsController = new SettingsController(this.bestelFacade);
		AdminView adminView = new AdminView();
		adminView.setOverviewController(broodjesOverviewController);
		adminView.setStatistiekController(statistiekController);
		adminView.setSettingsController(settingsController);
		BestellingView bestellingView = new BestellingView();
		KitchenView kitchenView = new KitchenView();
		BestelViewController bestelViewController = new BestelViewController(bestellingView, this.bestelFacade);
		KitchenViewController kitchenViewController = new KitchenViewController(kitchenView, this.bestelFacade);
	}

	@Override
	public void stop() {
		this.bestelFacade.saveData();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
