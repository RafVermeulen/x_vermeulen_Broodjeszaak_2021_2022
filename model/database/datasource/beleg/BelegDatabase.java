package model.database.datasource.beleg;

import model.Beleg;
import model.database.DbException;
import model.database.datasource.DatasourceFactory;

import java.io.IOException;
import java.util.TreeMap;

public class BelegDatabase {
    private static BelegDatabase instance;
    protected final TreeMap<String, Beleg> belegMap;
    protected final String filetype;

    public static BelegDatabase getInstance(String filetype) {
        if (instance == null) { instance = new BelegDatabase(filetype); }
        return instance;
    }

    private BelegDatabase(String filetype) {
        this.filetype = filetype;
        try { this.belegMap = DatasourceFactory.get(filetype, "Beleg").load(); }
        catch(RuntimeException | IOException e) {
            throw new DbException("Er liep iets mis met het inladen van de data voor beleg.");
        }
    }

    public TreeMap<String, Beleg> getBeleg() { return this.belegMap; }

    public Beleg get(String naamBeleg) {
        return this.belegMap.get(naamBeleg);
    }

    public void update(String naamBeleg, Beleg beleg) { this.belegMap.put(naamBeleg, beleg); }

    public void save() {
        try { DatasourceFactory.get(filetype, "Beleg").save(this.belegMap); }
        catch(IOException e) {
            throw new DbException("Er liep iets mis met het wegschrijven van de data voor beleg.");
        }
    }
}
