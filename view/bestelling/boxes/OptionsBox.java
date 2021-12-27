package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.database.observer.BestellingEventsEnum;

public class OptionsBox extends VBox {
    private ButtonsBox broodjesOptionsBox;
    private ButtonsBox belegOptionsBox;
    private BestelViewController controller;

    public OptionsBox() {
        this.broodjesOptionsBox = new ButtonsBox("broodje");
        this.belegOptionsBox = new ButtonsBox("beleg");

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setBackground(
                new Background(
                        new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(0))
                )
        );

        this.getChildren().addAll(this.broodjesOptionsBox, this.belegOptionsBox);
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
        this.broodjesOptionsBox.setController(controller);
        this.belegOptionsBox.setController(controller);
    }

    public void refresh() {
        this.broodjesOptionsBox.refresh(this.controller.getBroodjes(), BestellingEventsEnum.TOEVOEGEN_BROODJE);
        this.belegOptionsBox.refresh(this.controller.getBeleg(), BestellingEventsEnum.TOEVOEGEN_BELEG);
    }
}
