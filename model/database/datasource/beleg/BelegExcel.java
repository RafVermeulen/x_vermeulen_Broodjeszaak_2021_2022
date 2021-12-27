package model.database.datasource.beleg;

import model.database.datasource.ExcelTemplate;
import model.Beleg;
import model.database.datasource.Datasource;
import java.util.TreeMap;

public class BelegExcel extends ExcelTemplate implements Datasource {
    private TreeMap<String, Beleg> map = new TreeMap<>();

    public BelegExcel() {
        this.filename = "src/bestanden/beleg.xls";
    }

    @Override
    protected Beleg makeObject(String[] tokens) {
        return new Beleg(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
    }

    @Override
    protected String getKey(String[] tokens) {
        return tokens[0];
    }
}
