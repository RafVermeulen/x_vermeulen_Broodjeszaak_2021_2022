package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.database.observer.BestellingEventsEnum;
import model.bestelstates.BaseBestellingState;
import model.bestelstates.InWachtState;
import model.kortingStrategies.Korting;
import model.kortingStrategies.KortingFactory;
import model.kortingStrategies.KortingType;

public class Bestelling {
    private ObservableList<Bestellijn> bestellijnen;
    private BaseBestellingState state;
    private int bestelnummer;
    private Korting korting;

    public Bestelling(int bestelnummer) {
        this.bestelnummer = bestelnummer;
        this.state = new InWachtState(this);
        this.bestellijnen = FXCollections.observableArrayList();
        this.korting = KortingFactory.setKorting(KortingType.GEENKORTING);
    }

    public void setState(BaseBestellingState state) {
        this.state = state;
    }

    public void startBestelling() { this.state.startBestelling(); }
    public void toevoegenBroodje(Broodje broodje) { this.state.toevoegenBroodje(broodje); }
    public void verwijderBroodje(Bestellijn bestellijn) { this.state.verwijderBroodje(bestellijn); }
    public void toevoegenIdentiekBroodje(Bestellijn bestellijn) { this.state.toevoegenIdentiekBroodje(bestellijn); }
    public void toevoegenBeleg(Bestellijn bestellijn, Beleg beleg) { this.state.toevoegenBeleg(bestellijn, beleg); }
    public void afsluiten() { this.state.afsluiten(); }
    public void annuleren() { this.state.annuleren(); }
    public void betalen() { this.state.betalen(); }
    public void zendNaarKeuken() { this.state.zendNaarKeuken(); }
    public void startBereiding() { this.state.startBereiding(); }
    public void afgewerkt() { this.state.afgewerkt(); }

    public void addBestellijn(Bestellijn bestellijn) { this.bestellijnen.add(bestellijn); }

    public void updateBestellijn(int index, Bestellijn bestellijn) {
        this.bestellijnen.set(index, bestellijn);
    }

    public void deleteBestellijn(int index) {
        this.bestellijnen.remove(index);
    }

    public ObservableList<Bestellijn> getAllBestellijnen() {
        return this.bestellijnen;
    }

    public void aanpassenBestellingNummer(int amount) { this.bestelnummer += amount; }

    public int getBestelnummer() {
        return this.bestelnummer;
    }

    public void clear() {
        this.bestellijnen.clear();
    }

    public boolean isEventAllowed(BestellingEventsEnum event) {
        return this.state.isEventAllowed(event);
    }

    public double calculatePrice() {
        return this.korting.calculatePrice(this.bestellijnen);
    }

    public void setKorting(String kortingName) { this.korting = KortingFactory.setKorting(kortingName); }
}
