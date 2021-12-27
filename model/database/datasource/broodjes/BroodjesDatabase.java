package model.database.datasource.broodjes;

import model.Broodje;
import model.database.DbException;
import model.database.datasource.DatasourceFactory;

import java.io.IOException;
import java.util.TreeMap;

public class BroodjesDatabase {
    private static BroodjesDatabase instance;
    protected final TreeMap<String, Broodje> broodjesMap;
    protected final String filetype;

    public static BroodjesDatabase getInstance(String filetype) {
        if (instance == null) { instance = new BroodjesDatabase(filetype); }
        return instance;
    }

    private BroodjesDatabase(String filetype) {
        this.filetype = filetype;
        try { this.broodjesMap = DatasourceFactory.get(filetype, "Broodjes").load(); }
        catch(RuntimeException | IOException e) {
            throw new DbException("Er liep iets mis met het inladen van de data voor broodjes.");
        }
    }

    public TreeMap<String, Broodje> getBroodjes() {
        return this.broodjesMap;
    }
    public Broodje get(String naamBroodje) { return this.broodjesMap.get(naamBroodje); }
    public void update(String naamBroodje, Broodje broodje) { this.broodjesMap.put(naamBroodje, broodje); }
    public void save() {
        try { DatasourceFactory.get(filetype, "Broodjes").save(this.broodjesMap); }
        catch(IOException e) {
            throw new DbException("Er liep iets mis met het wegschrijven van de data voor broodjes.");
        }
    }
}
