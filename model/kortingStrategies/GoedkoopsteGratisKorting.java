package model.kortingStrategies;

import javafx.collections.ObservableList;
import model.Bestellijn;

public class GoedkoopsteGratisKorting implements Korting {
    public double calculatePrice(ObservableList<Bestellijn> bestellijnen) {
        double price = 0;
        double korting = -1;
        for(Bestellijn bestellijn : bestellijnen) {
            double lijnPrijs = bestellijn.calculatePrice();
            price += lijnPrijs;
            if (korting < 0 || lijnPrijs < korting) { korting = lijnPrijs; }
        }
        return price - korting;
    }
}
