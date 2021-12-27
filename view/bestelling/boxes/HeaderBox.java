package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import model.database.observer.BestellingEventsEnum;
import view.general.*;

public class HeaderBox extends HBox {
    private Button newBestellingBtn;
    private ChoiceBox<String> kortingSelect;
    private LabelNumberBox volgnrBox;
    private BestelViewController controller;

    public HeaderBox() {
        this.volgnrBox = new LabelNumberBox("Volgnr: ");
        this.newBestellingBtn = new Button("Nieuwe bestelling");
        this.kortingSelect = new ChoiceBox<>();

        this.newBestellingBtn.setOnAction(event -> controller.startBestelling());

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        this.getChildren().addAll(this.newBestellingBtn, this.volgnrBox, this.kortingSelect);
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
        ObservableList<String> kortingTypes = this.controller.getAllKortingTypes();
        this.kortingSelect.getItems().addAll(kortingTypes);
        this.kortingSelect.setOnAction(event -> controller.setKorting(this.kortingSelect.getValue()));
    }

    public void reset() {
        if (this.kortingSelect.getItems().size() > 0) {
            String defaultKorting = this.controller.getDefaultKorting();
            int defaultIndex = this.kortingSelect.getItems().indexOf(defaultKorting);
            if (defaultIndex < 0) { defaultIndex = 0; }
            this.kortingSelect.setValue(this.kortingSelect.getItems().get(defaultIndex));
        }
    }

    public void refresh() {
        this.volgnrBox.setNumber(this.controller.getBestelnummer());
        this.newBestellingBtn.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.START_BESTELLING));
        this.kortingSelect.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.AFSLUITEN_BESTELLING));
    }
}
