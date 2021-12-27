package model.bestelstates;

import model.*;
import model.database.observer.BestellingEventsEnum;

import java.util.ArrayList;

public class InBetaaldState extends BaseBestellingState {
    public InBetaaldState(Bestelling bestelling) {
        this.bestelling = bestelling;
        this.allowedEvents = new ArrayList<>();
        this.allowedEvents.add(BestellingEventsEnum.ZEND_NAAR_KEUKEN);
    }

    public void zendNaarKeuken() { this.bestelling.setState(new InWachtrijState(this.bestelling)); }
}
