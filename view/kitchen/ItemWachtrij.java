package view.kitchen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Bestellijn;
import model.Bestelling;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemWachtrij extends GridPane {
    private boolean empty;
    public ItemWachtrij() {
        this.empty = true;
    }

    public boolean isEmpty() { return this.empty; }

    public void clearBestelling() {
        this.getChildren().clear();
        BorderStroke[] strokes = new BorderStroke[4];
        Border border = new Border(strokes);
        this.setBorder(border);
        this.empty = true;
    }

    public void showData(Bestelling bestelling) {
        this.init();
        this.empty = false;

        Collection<Bestellijn> bestellijnen = bestelling.getAllBestellijnen();
        String bestellingHeader = "Volgnummer bestelling: " + bestelling.getBestelnummer();
        bestellingHeader += " - Aantal broodjes:" + bestellijnen.size();
        Label header = new Label(bestellingHeader);
        this.add(header,0,0);

        HashMap<String, Integer> lines = new HashMap<>();

        for (Bestellijn bestellijn : bestellijnen) {
            String line = bestellijn.getSummary();
            int count = 1;
            if (lines.containsKey(line)) { count = lines.get(line) + 1; }
            lines.put(line, count);
        }

        int rowIndex = 3;
        for (Map.Entry line : lines.entrySet()) {
            int times = (int) line.getValue();
            String value = (String) line.getKey();
            String regel = times + " x " + value;
            this.add(new Label(regel), 0, rowIndex);
            rowIndex++;
        }
    }

    private void init() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        BorderStroke[] strokes = new BorderStroke[4];
        for (int i = 0; i < 4; i++) {
            strokes[i] = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1));
        }
        Border border = new Border(strokes);
        this.setBorder(border);
    }
}
