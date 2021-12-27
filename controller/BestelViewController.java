package controller;

import javafx.collections.ObservableList;
import model.Beleg;
import model.Bestellijn;
import model.Broodje;
import model.Item;
import model.database.BestelFacade;
import model.database.observer.BestellingEventsEnum;
import view.BestellingView;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class BestelViewController implements Observer {
    private BestellingView view;
    private BestelFacade bestelFacade;

    public BestelViewController(BestellingView view, BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
        this.view = view;
        this.view.setController(this);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.TOEVOEGEN_BROODJE);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.TOEVOEGEN_BELEG);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.TOEVOEGEN_IDENTIEK_BROODJE);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.VERWIJDER_BROODJE);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.ANNULEREN);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.AFSLUITEN_BESTELLING);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.BETALEN);
        this.bestelFacade.addObserver(this, BestellingEventsEnum.ZEND_NAAR_KEUKEN);
        this.view.refresh();
    }

    public void startBestelling() {
        this.bestelFacade.startBestelling();
        this.view.reset();
        this.view.refresh();
    }

    public void update(Observable observable, Object obj) { this.view.refresh(); }
    public int getBestelnummer() { return this.bestelFacade.getBestelnummer(); }
    public ObservableList<Bestellijn> getBestellijnen() { return this.bestelFacade.getAllBestellijnen(); }
    public Collection<Broodje> getBroodjes() { return this.bestelFacade.getAllBroodjes().values(); }
    public Collection<Beleg> getBeleg() { return this.bestelFacade.getAllBeleg().values(); }
    public ObservableList<String> getAllKortingTypes() { return this.bestelFacade.getAllKortingTypes(); }
    public boolean isEventAllowed(BestellingEventsEnum event) { return this.bestelFacade.isEventAllowed(event); }
    public double calculatePrice() { return this.bestelFacade.calculatePrice(); }
    public void setKorting(String kortingName) { this.bestelFacade.setKorting(kortingName); }
    public void annuleer() { this.bestelFacade.annuleerBestelling(); }
    public void afsluiten() { this.bestelFacade.afsluiten(); }
    public void betalen() { this.bestelFacade.betalen(); }
    public void zendNaarKeuken() { this.bestelFacade.zendNaarKeuken(); }
    public void toevoegenBroodje(Item item) { this.bestelFacade.toevoegenBroodje(item.getName()); }

    public void toevoegenBeleg(Item item) {
        try { this.bestelFacade.toevoegenBeleg(this.view.getSelectedBestellijn(), item.getName()); }
        catch(IllegalArgumentException e) { this.view.showError(e.getMessage()); }
    }

    public void toevoegenIdentiekBroodje() {
        try { this.bestelFacade.toevoegenIdentiekBroodje(this.view.getSelectedBestellijn()); }
        catch(IllegalArgumentException e) { this.view.showError(e.getMessage()); }
    }

    public void verwijderBroodje() {
        try { this.bestelFacade.verwijderBroodje(this.view.getSelectedBestellijn()); }
        catch(IllegalArgumentException e) { this.view.showError(e.getMessage()); }
    }

    public String getDefaultKorting() { return this.bestelFacade.getDefaultKorting(); }
}
