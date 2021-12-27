package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.database.observer.BestellingEventsEnum;

public class BestellijnActionsBox extends VBox {
    private VBox actionsBox;
    private Label instructionLabel;
    private Button duplicateBtn;
    private Button deleteBtn;
    private Button cancelBtn;
    private BestelViewController controller;

    public BestellijnActionsBox() {
        this.actionsBox = new VBox(20);
        this.actionsBox.setPadding(new Insets(10));
        this.actionsBox.setAlignment(Pos.CENTER);
        this.actionsBox.setBackground(
                new Background(
                        new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(0))
                )
        );

        this.instructionLabel = new Label("Selecteer lijn in lijst");
        this.duplicateBtn = new Button("Voeg zelfde broodje toe");
        this.duplicateBtn.setOnAction(event -> this.controller.toevoegenIdentiekBroodje());
        this.duplicateBtn.setDisable(true);
        this.deleteBtn = new Button("Verwijder broodje");
        this.deleteBtn.setOnAction(event -> this.controller.verwijderBroodje());
        this.deleteBtn.setDisable(true);

        this.actionsBox.getChildren().addAll(this.instructionLabel, this.duplicateBtn, this.deleteBtn);

        this.cancelBtn = new Button("Annuleer bestelling");
        this.cancelBtn.setDisable(true);
        this.cancelBtn.setOnAction(event -> this.controller.annuleer());

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        this.getChildren().addAll(this.actionsBox, this.cancelBtn);
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
    }

    public void refresh() {
        this.duplicateBtn.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.TOEVOEGEN_IDENTIEK_BROODJE));
        this.deleteBtn.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.VERWIJDER_BROODJE));
        this.cancelBtn.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.ANNULEREN));
    }
}
