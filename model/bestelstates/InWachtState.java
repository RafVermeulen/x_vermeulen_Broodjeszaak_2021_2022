package model.bestelstates;

import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InWachtState extends BaseBestellingState {
    public InWachtState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.START_BESTELLING);
    }

    public void startBestelling() {
        this.bestelling.aanpassenBestellingNummer(1);
        this.bestelling.setState(new InBestellingState(this.bestelling));
    }
}
