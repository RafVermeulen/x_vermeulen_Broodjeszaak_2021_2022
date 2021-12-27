package controller;

import model.*;
import model.database.BestelFacade;
import model.database.observer.BestellingEventsEnum;
import view.KitchenView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class KitchenViewController implements Observer {
    private KitchenView view;
    private BestelFacade bestelFacade;

    public KitchenViewController(KitchenView view, BestelFacade bestelFacade) {
        this.view = view;
        this.bestelFacade = bestelFacade;
        this.bestelFacade.addObserver(this, BestellingEventsEnum.ZEND_NAAR_KEUKEN);
        this.view.setController(this);
        this.view.refresh();
    }

    public void update(Observable observable, Object obj) { this.view.refresh(); }

    public Bestelling getVolgendeBestelling() {
        if (this.bestelFacade.getWachtrij().size() == 0) {
            return null;
        }
        return this.bestelFacade.getWachtrij().remove(0);
    }

    public ArrayList<Bestelling> getWachtrij() {
        return this.bestelFacade.getWachtrij();
    }
}
