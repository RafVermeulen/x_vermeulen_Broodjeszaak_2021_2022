package model.bestelstates;

import javafx.collections.ObservableList;
import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public abstract class BaseBestellingState {
    protected Bestelling bestelling;
    protected ArrayList<BestellingEventsEnum> allowedEvents;

    public void startBestelling() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void toevoegenBroodje(Broodje broodje) { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); };
    public void verwijderBroodje(Bestellijn bestellijn) { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void toevoegenIdentiekBroodje(Bestellijn bestellijn) { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void toevoegenBeleg(Bestellijn bestellijn, Beleg beleg) { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void afsluiten() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void annuleren() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void betalen() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void zendNaarKeuken() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void startBereiding() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }
    public void afgewerkt() { throw new RuntimeException("Deze actie is in deze toestand niet toegestaan."); }

    public boolean isEventAllowed(BestellingEventsEnum event) {
        return (this.allowedEvents.contains(event));
    }
    public void reset() {
        ObservableList<Bestellijn> bestellijnen = this.bestelling.getAllBestellijnen();
        for (Bestellijn bestellijn : bestellijnen) {
            bestellijn.clear();
        }
        this.bestelling.aanpassenBestellingNummer(-1);
        this.bestelling.clear();
        this.bestelling.setState(new InWachtState(this.bestelling));
    }
}
