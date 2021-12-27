package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import model.Bestellijn;

public class DetailsBox extends HBox {
    private TableView bestellijnenTable;
    private BestellijnActionsBox bestellijnActionsBox;
    private BestelViewController controller;

    public DetailsBox() {
        this.bestellijnActionsBox = new BestellijnActionsBox();
        this.bestellijnenTable = this.createBestellijnenTable();
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(20);

        this.getChildren().addAll(this.bestellijnenTable, this.bestellijnActionsBox);
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
        this.bestellijnActionsBox.setController(controller);
    }

    public TableView<Bestellijn> createBestellijnenTable() {
        TableView table = new javafx.scene.control.TableView<Bestellijn>();
        TableColumn<Bestellijn, String> colName = new TableColumn<Bestellijn, String>("Broodje");
        colName.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("foundation"));

        TableColumn<Bestellijn, String> colBeleg = new TableColumn<Bestellijn, String>("Beleg");
        colBeleg.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("description"));

        table.getColumns().addAll(colName, colBeleg);
        return table;
    }

    public Bestellijn getSelectedBestellijn() {
        TableViewSelectionModel<Bestellijn> selectedRow = this.bestellijnenTable.getSelectionModel();
        Bestellijn bestellijn = selectedRow.getSelectedItem();
        return bestellijn;
    }

    public void refresh() {
        ObservableList<Bestellijn> bestellijnen = this.controller.getBestellijnen();
        this.bestellijnenTable.setItems(bestellijnen);
        this.bestellijnenTable.refresh();
        this.bestellijnActionsBox.refresh();
    }
}
