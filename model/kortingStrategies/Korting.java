package model.kortingStrategies;

import javafx.collections.ObservableList;
import model.Bestellijn;

public interface Korting {
    double calculatePrice(ObservableList<Bestellijn> bestellijnen);
}
