package model.kortingStrategies;

import javafx.collections.ObservableList;
import model.Bestellijn;

public class TienProcentKorting implements Korting {
    public double calculatePrice(ObservableList<Bestellijn> bestellijnen) {
        double price = 0;
        for(Bestellijn bestellijn : bestellijnen) {
            price += bestellijn.calculatePrice();
        }
        return price * 0.9;
    }
}
