package view.bestelling;

import controller.BestelViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Bestellijn;
import view.bestelling.boxes.*;
import view.general.*;

public class BestellingPane extends BorderPane {
    private HeaderBox headerBox;
    private OptionsBox optionsBox;
    private DetailsBox detailsBox;
    private PaymentBox paymentBox;
    private LabelNumberBox labelNumberBox;
    private BestelViewController controller;

	public BestellingPane() {
        this.headerBox = new HeaderBox();
        this.optionsBox = new OptionsBox();
        this.detailsBox = new DetailsBox();
        this.paymentBox = new PaymentBox();
        this.labelNumberBox = new LabelNumberBox("Aantal besteld: ");

        VBox mainBox = new VBox(8);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(
                this.headerBox,
                this.optionsBox,
                this.labelNumberBox,
                this.detailsBox,
                this.paymentBox
        );

        this.setCenter(mainBox);
	}

    public void setController(BestelViewController controller) {
        this.controller = controller;
        this.headerBox.setController(controller);
        this.optionsBox.setController(controller);
        this.detailsBox.setController(controller);
        this.paymentBox.setController(controller);
    }

    public Bestellijn getSelectedBestellijn() {
        return this.detailsBox.getSelectedBestellijn();
    }

    public void reset() { this.headerBox.reset(); }

    public void refresh() {
        this.headerBox.refresh();
        this.optionsBox.refresh();
        this.detailsBox.refresh();
        this.paymentBox.refresh();
        this.labelNumberBox.setNumber(this.controller.getBestellijnen().size());
    }
}
