package controller;

import model.Beleg;
import model.Broodje;
import model.database.BestelFacade;
import model.database.observer.BestellingEventsEnum;
import view.admin.panels.StatistiekPane;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

public class StatistiekController implements Observer {
    private StatistiekPane view;
    private BestelFacade bestelFacade;

    public StatistiekController(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
        this.bestelFacade.addObserver(this, BestellingEventsEnum.ZEND_NAAR_KEUKEN);
    }

    public void setView(StatistiekPane view) {
        this.view = view;
        this.view.refresh();
    }

    public void update(Observable observable, Object obj) {
        this.view.refresh();
    }

    public Collection<Broodje> getBroodjes() { return this.bestelFacade.getAllBroodjes().values(); }
    public Collection<Beleg> getBeleg() { return this.bestelFacade.getAllBeleg().values(); }
}
