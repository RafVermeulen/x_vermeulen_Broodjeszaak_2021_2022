package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.database.observer.BestellingEventsEnum;
import view.general.*;

public class PaymentBox extends HBox {
    private Button closeBestellingButton;
    private Button payButton;
    private Button kitchenButton;
    private LabelNumberBox priceBox;
    private BestelViewController controller;

    public PaymentBox() {
        this.closeBestellingButton = new Button("Afsluiten bestelling");
        this.closeBestellingButton.setOnAction(event -> this.controller.afsluiten());
        this.closeBestellingButton.setDisable(true);
        this.priceBox = new LabelNumberBox("Te betalen: €");
        this.priceBox.setNumberColor(Color.RED);
        this.payButton = new Button("Betaal");
        this.payButton.setOnAction(event -> this.controller.betalen());
        this.payButton.setDisable(true);
        this.kitchenButton = new Button("Naar keuken");
        this.kitchenButton.setOnAction(event -> this.controller.zendNaarKeuken());
        this.kitchenButton.setDisable(true);

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setBackground(
                new Background(
                        new BackgroundFill(Color.SKYBLUE, new CornerRadii(5), new Insets(0))
                )
        );

        this.getChildren().addAll(
                this.closeBestellingButton,
                this.priceBox,
                this.payButton,
                this.kitchenButton
        );
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
    }

    public void refresh() {
        this.closeBestellingButton.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.AFSLUITEN_BESTELLING));
        this.kitchenButton.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.ZEND_NAAR_KEUKEN));
        this.payButton.setDisable(!this.controller.isEventAllowed(BestellingEventsEnum.BETALEN));

        if (this.controller.isEventAllowed(BestellingEventsEnum.START_BESTELLING)) {
            this.priceBox.setNumber("-");
        }

        if (this.controller.isEventAllowed(BestellingEventsEnum.BETALEN)) {
            this.priceBox.setNumber(this.controller.calculatePrice());
        }
    }
}
