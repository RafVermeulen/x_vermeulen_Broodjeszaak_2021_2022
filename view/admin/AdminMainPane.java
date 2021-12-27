package view.admin;

import controller.BroodjesOverviewController;
import controller.SettingsController;
import controller.StatistiekController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.admin.panels.BroodjesOverviewPane;
import view.admin.panels.SettingsPane;
import view.admin.panels.StatistiekPane;

public class AdminMainPane extends BorderPane {
    private BroodjesOverviewPane broodjesOverviewPane;
    private StatistiekPane statistiekPane;
    private SettingsPane settingsPane;

	public AdminMainPane(){		
	    TabPane tabPane = new TabPane();
        this.broodjesOverviewPane = new BroodjesOverviewPane();
        this.statistiekPane = new StatistiekPane();
        this.settingsPane = new SettingsPane();
        Tab broodjesTab = new Tab("Broodjes/Beleg",this.broodjesOverviewPane);
        Tab instellingTab = new Tab("Instellingen", this.settingsPane);
        Tab statistiekTab = new Tab("Statistieken",this.statistiekPane);
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}

    public void refresh() {
        this.broodjesOverviewPane.refresh();
        this.statistiekPane.refresh();
    }

    public void setOverviewController(BroodjesOverviewController controller) {
        this.broodjesOverviewPane.setController(controller);
    }

    public void setStatistiekController(StatistiekController controller) {
        this.statistiekPane.setController(controller);
    }

    public void setSettingsController(SettingsController controller) {
        this.settingsPane.setController(controller);
    }
}
