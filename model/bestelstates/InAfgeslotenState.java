package model.bestelstates;

import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InAfgeslotenState extends BaseBestellingState {
    public InAfgeslotenState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.BETALEN);
        this.allowedEvents.add(BestellingEventsEnum.ANNULEREN);
    }

    public void annuleren() { this.reset(); }
    public void betalen() { this.bestelling.setState(new InBetaaldState(this.bestelling)); }
}
