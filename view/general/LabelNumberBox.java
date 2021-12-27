package view.general;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LabelNumberBox extends HBox {
    private Label label;
    private Label number;

    public LabelNumberBox(String label) {
        this.label = new Label(label);
        this.number = new Label("-");

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);

        this.getChildren().addAll(this.label, this.number);
    }

    public void setNumberColor(Color color) {
        this.number.setTextFill(color);
    }

    public void setNumber(String number) {
        this.number.setText(number);
    }

    public void setNumber(int number) {
        this.number.setText(Integer.toString(number));
    }

    public void setNumber(Double number) { this.number.setText(String.format("%.2f", number)); }
}
