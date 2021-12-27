package model.bestelstates;

import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InWachtrijState extends BaseBestellingState {
    public InWachtrijState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.START_BEREIDING);
    }
}
