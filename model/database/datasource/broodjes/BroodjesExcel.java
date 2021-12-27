package model.database.datasource.broodjes;

import model.Broodje;
import model.database.datasource.ExcelTemplate;
import model.database.datasource.Datasource;

import java.util.TreeMap;

public class BroodjesExcel extends ExcelTemplate implements Datasource {
    protected TreeMap<String, Broodje> map = new TreeMap<>();

    public BroodjesExcel() {
        this.filename = "src/bestanden/broodjes.xls";
    }

    @Override
    protected Broodje makeObject(String[] tokens) {
        return new Broodje(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    protected String getKey(String[] tokens) {
        return tokens[0];
    }
}
