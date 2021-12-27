package view.admin.panels;

import controller.SettingsController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class SettingsPane extends GridPane{
	private SettingsController controller;
	private ChoiceBox<String> datasourceSelect;
	private ChoiceBox<String> kortingSelect;

	public SettingsPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		this.datasourceSelect = new ChoiceBox<>();
		this.kortingSelect = new ChoiceBox<>();

		Button saveBtn = new Button("Bewaar");
		saveBtn.setOnAction(event -> this.controller.saveSettings());

		this.add(new Label("Data source: "), 0, 0);
		this.add(this.datasourceSelect, 1, 0);
		this.add(new Label("Default korting: "), 0, 1);
		this.add(this.kortingSelect, 1, 1);
		this.add(saveBtn, 0, 2, 2, 1);
	}

	public void refresh() {}

	public void setController(SettingsController controller) {
		this.controller = controller;
		this.controller.setView(this);
		this.setKortingSelect();
		this.setDatasourceSelect();
	}

	public void setKortingSelect() {
		ObservableList<String> kortingTypes = this.controller.getAllKortingTypes();
		String defaultKorting = this.controller.getDefaultKorting();

		this.kortingSelect.getItems().addAll(kortingTypes);
		int defaultIndex = this.kortingSelect.getItems().indexOf(defaultKorting);
		if (defaultIndex < 0) { defaultIndex = 0; }
		this.kortingSelect.setValue(this.kortingSelect.getItems().get(defaultIndex));
		this.kortingSelect.setOnAction(event -> this.controller.setDefaultKorting(this.kortingSelect.getValue()));
	}

	public void setDatasourceSelect() {
		ObservableList<String> datasourceTypes = this.controller.getAllDatasourceTypes();
		String datasource = this.controller.getDatasource();

		this.datasourceSelect.getItems().addAll(datasourceTypes);
		int defaultIndex = this.datasourceSelect.getItems().indexOf(datasource);
		if (defaultIndex < 0) { defaultIndex = 0; }
		this.datasourceSelect.setValue(this.datasourceSelect.getItems().get(defaultIndex));
		this.datasourceSelect.setOnAction(event -> this.controller.setDatasource(this.datasourceSelect.getValue()));
	}
}
