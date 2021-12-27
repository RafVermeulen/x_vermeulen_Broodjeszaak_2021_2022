package view.admin.panels;

import controller.BestelViewController;
import controller.BroodjesOverviewController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Item;
import model.Broodje;
import model.Beleg;

public class BroodjesOverviewPane extends GridPane{
	private TableView<Item> broodjesTable;
	private TableView<Item> belegTable;
	private BroodjesOverviewController controller;
	
	public BroodjesOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		this.broodjesTable = this.createBroodjesTable();
		this.belegTable = this.createBelegTable();

		this.add(new Label("Broodjes:"), 0, 0, 1, 1);
		this.add(this.broodjesTable, 0, 1, 3, 1);

		this.add(new Label("Beleg:"), 4, 0, 1, 1);
		this.add(belegTable, 4, 1, 3, 1);
	}

	public TableView<Item> createBroodjesTable() {
		TableView table = new TableView<Broodje>();
		TableColumn<Broodje, String> colName = new TableColumn<Broodje, String>("Naam");
		colName.setCellValueFactory(new PropertyValueFactory<Broodje, String>("name"));

		TableColumn<Broodje, Double> colPrice = new TableColumn<Broodje, Double>("Prijs");
		colPrice.setCellValueFactory(new PropertyValueFactory<Broodje, Double>("Price"));

		TableColumn<Broodje, Integer> colStock = new TableColumn<Broodje, Integer>("Voorraad");
		colStock.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("Stock"));

		table.getColumns().addAll(colName, colPrice, colStock);
		return table;
	}

	public TableView<Item> createBelegTable() {
		TableView table = new TableView<Beleg>();
		TableColumn<Beleg, String> colName = new TableColumn<Beleg, String>("Naam");
		colName.setCellValueFactory(new PropertyValueFactory<Beleg, String>("name"));

		TableColumn<Beleg, Double> colPrice = new TableColumn<Beleg, Double>("Prijs");
		colPrice.setCellValueFactory(new PropertyValueFactory<Beleg, Double>("Price"));

		TableColumn<Beleg, Integer> colStock = new TableColumn<Beleg, Integer>("Voorraad");
		colStock.setCellValueFactory(new PropertyValueFactory<Beleg, Integer>("Stock"));

		table.getColumns().addAll(colName, colPrice, colStock);
		return table;
	}

	public void refresh() {
		this.broodjesTable.setItems(FXCollections.observableArrayList(this.controller.getBroodjes()));
		this.belegTable.setItems(FXCollections.observableArrayList(this.controller.getBeleg()));

		this.broodjesTable.refresh();
		this.belegTable.refresh();
	}

	public void setController(BroodjesOverviewController controller) {
		this.controller = controller;
		this.controller.setView(this);
	}
}
