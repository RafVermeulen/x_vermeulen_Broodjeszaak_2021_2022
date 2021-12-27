package model.database;

import javafx.collections.ObservableList;
import model.*;
import model.database.datasource.broodjes.BroodjesDatabase;
import model.database.datasource.beleg.BelegDatabase;
import model.database.datasource.DatasourceType;
import model.database.observer.BestellingEventsEnum;
import model.database.observer.Subject;
import model.kortingStrategies.KortingType;

import java.util.ArrayList;
import java.util.TreeMap;

public class BestelFacade extends Subject {
    private BroodjesDatabase broodjes;
    private BelegDatabase beleg;
    private Bestelling bestelling;
    private ArrayList<Bestelling> wachtrij;
    private int bestelnummer;
    private Settings settings;

    public BestelFacade() {
        try {
            this.settings = Settings.getInstance();
            this.bestelnummer = 0;
            this.broodjes = BroodjesDatabase.getInstance(this.settings.getDatasource());
            this.beleg = BelegDatabase.getInstance(this.settings.getDatasource());
            this.bestelling = new Bestelling(this.bestelnummer);
            this.wachtrij = new ArrayList<>();
        }
        catch(DbException e){
            System.out.println(e.getMessage());
            System.exit(500);
        }
    }

    public int getBestelnummer() { return this.bestelnummer; }

    public TreeMap<String, Broodje> getAllBroodjes() { return broodjes.getBroodjes(); }

    public TreeMap<String, Beleg> getAllBeleg() { return beleg.getBeleg(); }

    public ObservableList<Bestellijn> getAllBestellijnen() { return this.bestelling.getAllBestellijnen(); }

    public void startBestelling() {
        try {
            this.bestelling.startBestelling();
            this.bestelnummer = this.bestelling.getBestelnummer();
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    public void toevoegenBeleg(Bestellijn bestellijn, String naamBeleg) {
        try {
            this.bestelling.toevoegenBeleg(bestellijn, this.beleg.get(naamBeleg));
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.TOEVOEGEN_BELEG);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void toevoegenBroodje(String naamBroodje) {
        try {
            this.bestelling.toevoegenBroodje(this.broodjes.get(naamBroodje));
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.TOEVOEGEN_BROODJE);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void toevoegenIdentiekBroodje(Bestellijn bestellijn) {
        try {
            this.bestelling.toevoegenIdentiekBroodje(bestellijn);
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.TOEVOEGEN_IDENTIEK_BROODJE);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void verwijderBroodje(Bestellijn bestellijn) {
        try {
            this.bestelling.verwijderBroodje(bestellijn);
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.VERWIJDER_BROODJE);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void annuleerBestelling() {
        try {
            this.bestelling.annuleren();
            this.bestelnummer = this.bestelling.getBestelnummer();
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.ANNULEREN);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void afsluiten() {
        try {
            this.bestelling.afsluiten();
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.AFSLUITEN_BESTELLING);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void betalen() {
        try {
            this.bestelling.betalen();
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.BETALEN);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void zendNaarKeuken() {
        try {
            this.bestelling.zendNaarKeuken();
            this.wachtrij.add(bestelling);
            this.bestelling = new Bestelling(this.bestelnummer);
            this.setChanged();
            this.notifyObservers(BestellingEventsEnum.ZEND_NAAR_KEUKEN);
        }
        catch(RuntimeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Bestelling> getWachtrij() { return this.wachtrij; }

    public Broodje getBroodje(String naamBroodje) { return this.broodjes.get(naamBroodje); }

    public Beleg getBeleg(String naamBeleg) { return this.beleg.get(naamBeleg); }

    public void updateBroodje(String naamBroodje, Broodje broodje) { this.broodjes.update(naamBroodje, broodje); }

    public void updateBeleg(String naamBeleg, Beleg beleg) { this.beleg.update(naamBeleg, beleg); }

    public double calculatePrice() { return this.bestelling.calculatePrice(); }

    public boolean isEventAllowed(BestellingEventsEnum event) { return this.bestelling.isEventAllowed(event); }

    public ObservableList<String> getAllKortingTypes() { return KortingType.getAllAsList(); }

    public ObservableList<String> getAllDatasourceTypes() { return DatasourceType.getAllAsList(); }

    public void setKorting(String kortingName) { this.bestelling.setKorting(kortingName); }

    public String getDefaultKorting() { return this.settings.getDefaultKorting(); }

    public void setDefaultKorting(String korting) { this.settings.setDefaultKorting(korting); }

    public String getDatasource() { return this.settings.getDatasource(); }

    public void setDatasource(String datasource) { this.settings.setDatasource(datasource); }

    public void saveSettings() { this.settings.save(); }

    public void saveData() {
        this.beleg.save();
        this.broodjes.save();
    }
}