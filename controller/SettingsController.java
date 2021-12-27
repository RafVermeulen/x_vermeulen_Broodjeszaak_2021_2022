package controller;

import javafx.collections.ObservableList;
import model.database.BestelFacade;
import view.admin.panels.SettingsPane;
import java.util.Observable;
import java.util.Observer;

public class SettingsController implements Observer {
    private SettingsPane view;
    private BestelFacade bestelFacade;

    public SettingsController(BestelFacade bestelFacade) {
        this.bestelFacade = bestelFacade;
    }

    public void setView(SettingsPane view) { this.view = view; }

    public void update(Observable observable, Object obj) {}

    public ObservableList<String> getAllKortingTypes() { return this.bestelFacade.getAllKortingTypes(); }
    public ObservableList<String> getAllDatasourceTypes() { return this.bestelFacade.getAllDatasourceTypes(); }

    public String getDefaultKorting() { return this.bestelFacade.getDefaultKorting(); }
    public void setDefaultKorting(String korting) { this.bestelFacade.setDefaultKorting(korting); }
    public String getDatasource() { return this.bestelFacade.getDatasource(); }
    public void setDatasource(String datasource) { this.bestelFacade.setDatasource(datasource); }
    public void saveSettings() { this.bestelFacade.saveSettings(); }
}
