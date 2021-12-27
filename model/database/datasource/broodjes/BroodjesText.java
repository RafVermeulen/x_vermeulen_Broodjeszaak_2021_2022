package model.database.datasource.broodjes;

import model.Broodje;
import model.database.datasource.Datasource;
import model.database.datasource.TextTemplate;
import java.util.TreeMap;

public class BroodjesText extends TextTemplate implements Datasource {
    private TreeMap<String, Broodje> map = new TreeMap<>();

    public BroodjesText() {
        this.filename = "src/bestanden/broodjes.txt";
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
