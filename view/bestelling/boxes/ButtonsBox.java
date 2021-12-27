package view.bestelling.boxes;

import controller.BestelViewController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import model.Item;
import model.database.observer.BestellingEventsEnum;

import java.util.Collection;

public class ButtonsBox extends HBox {
    private BestelViewController controller;
    private String type;

    public ButtonsBox(String type) {
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_LEFT);
        this.setSpacing(20);
        this.type = type;
    }

    public void setController(BestelViewController controller) {
        this.controller = controller;
    }

    public void refresh(Collection items, BestellingEventsEnum stateEvent) {
        this.getChildren().clear();
        items = FXCollections.observableArrayList(items);
        for (Object obj : items) {
            Item item = (Item) obj;
            Button button = new Button(item.getName());
            boolean buttonEnabled = (item.getStock() > 0 && this.controller.isEventAllowed(stateEvent));
            button.setDisable(!buttonEnabled);
            switch(this.type.toLowerCase()) {
                case "broodje":
                    button.setOnAction(event -> this.controller.toevoegenBroodje(item));
                    break;
                case "beleg":
                    button.setOnAction(event -> this.controller.toevoegenBeleg(item));
                    break;
            }
            this.getChildren().add(button);
        }
    }
}
