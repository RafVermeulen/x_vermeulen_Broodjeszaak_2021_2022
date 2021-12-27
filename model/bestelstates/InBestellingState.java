package model.bestelstates;

import javafx.collections.ObservableList;
import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InBestellingState extends BaseBestellingState {
    public InBestellingState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.TOEVOEGEN_BROODJE);
        this.allowedEvents.add(BestellingEventsEnum.TOEVOEGEN_BELEG);
        this.allowedEvents.add(BestellingEventsEnum.TOEVOEGEN_IDENTIEK_BROODJE);
        this.allowedEvents.add(BestellingEventsEnum.VERWIJDER_BROODJE);
        this.allowedEvents.add(BestellingEventsEnum.AFSLUITEN_BESTELLING);
        this.allowedEvents.add(BestellingEventsEnum.ANNULEREN);
    }

    public void toevoegenBroodje(Broodje broodje) {
        BestelBroodje bestellijn = new BestelBroodje(broodje.getName());
        this.bestelling.addBestellijn(bestellijn);
    }

    public void verwijderBroodje(Bestellijn bestellijn) {
        if (bestellijn == null) {
            throw new IllegalArgumentException("U moet een bestellijn selecteren om een bestellijn te kunnen verwijderen.");
        }
        ObservableList<Bestellijn> bestellijnen = this.bestelling.getAllBestellijnen();
        int index = bestellijnen.indexOf(bestellijn);
        bestellijn.clear();
        this.bestelling.deleteBestellijn(index);
    }

    public void toevoegenIdentiekBroodje(Bestellijn bestellijn) {
        if (bestellijn == null) {
            throw new IllegalArgumentException("U moet een bestellijn selecteren om een bestellijn te kunnen dupliceren.");
        }
        Bestellijn nieuweBestellijn = bestellijn.copy();
        this.bestelling.addBestellijn(nieuweBestellijn);
    }

    public void toevoegenBeleg(Bestellijn bestellijn, Beleg beleg) {
        if (bestellijn == null) {
            throw new IllegalArgumentException("U moet een bestellijn selecteren om daar beleg aan toe te kunnen voegen.");
        }
        ObservableList<Bestellijn> bestellijnen = this.bestelling.getAllBestellijnen();
        int index = bestellijnen.indexOf(bestellijn);
        BestelBeleg bestelBeleg = new BestelBeleg(beleg.getName(), bestellijn);
        this.bestelling.updateBestellijn(index, bestelBeleg);
    }

    public void afsluiten() { this.bestelling.setState(new InAfgeslotenState(this.bestelling)); }
    public void annuleren() { this.reset(); }
}
