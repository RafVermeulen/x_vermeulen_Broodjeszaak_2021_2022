package model.bestelstates;

import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InBereidingState extends BaseBestellingState {
    public InBereidingState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.AFWERKEN);
    }
}
