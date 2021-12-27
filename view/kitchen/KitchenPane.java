package view.kitchen;

import controller.KitchenViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Bestelling;
import view.general.LabelNumberBox;

public class KitchenPane extends GridPane {
    private KitchenViewController controller;
    private ItemWachtrij bestelling;
    private Button nextBtn;
    private Button finishBtn;
    private LabelNumberBox counterBox;

	public KitchenPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.bestelling = new ItemWachtrij();

        this.nextBtn = new Button("Volgende bestelling");
        nextBtn.setOnAction(event -> this.getBestelling());
        this.finishBtn = new Button("Bestelling afgewerkt");
        finishBtn.setOnAction(event -> this.bestellingAfgewerkt());

        this.counterBox = new LabelNumberBox("In wachtrij: ");
        this.add(this.counterBox, 0, 0, 2, 1);
        this.add(this.nextBtn, 0, 1);
        this.add(this.finishBtn, 1, 1);
        this.add(this.bestelling, 0, 2, 2, 1);
	}

    public void setController(KitchenViewController controller) {
        this.controller = controller;
        this.refresh();
    }

    public void getBestelling() {
        Bestelling bestelling = this.controller.getVolgendeBestelling();

        if (bestelling != null) {
            this.bestelling.showData(bestelling);
            this.refresh();
        }
    }

    public void bestellingAfgewerkt() {
        this.bestelling.clearBestelling();
        this.refresh();
    }

    private void setButtons() {
        if (this.bestelling.isEmpty()) { this.finishBtn.setDisable(true); }
        else { this.finishBtn.setDisable(false); }

        if (this.controller.getWachtrij().size() == 0 || !this.bestelling.isEmpty()) { this.nextBtn.setDisable(true); }
        else { this.nextBtn.setDisable(false); }
    }

    public void refresh() {
        this.setButtons();
        this.counterBox.setNumber(this.controller.getWachtrij().size());
    }
}
