package model.database.datasource;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public abstract class ExcelTemplate<K,V> {
    protected TreeMap<K,V> map = new TreeMap<>();
    protected String filename;

    public final TreeMap<K,V> load() throws IOException {
        ExcelPlugin excel = new ExcelPlugin();
        TreeMap<K,V> returnMap = new TreeMap<>();

        File file = new File(this.filename);

        try {
            ArrayList<ArrayList<String>> lines = excel.read(file);
            for(ArrayList<String> line : lines) {
                String[] tokens = new String[line.size()];
                for (int i = 0; i < line.size(); i++) {
                    tokens[i] = line.get(i);
                }
                V element = makeObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key, element);
            }
        }
        catch(BiffException | IOException e) {
            throw new IOException("Failed reading XLS-file.");
        }

        return returnMap;
    }

    public final void save(TreeMap<K,V> items) throws IOException {
        ExcelPlugin excel = new ExcelPlugin();
        File file = new File(this.filename);

        ArrayList<ArrayList<String>> results = new ArrayList<>();

        for (Object obj : items.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            Item item = (Item) entry.getValue();
            ArrayList<String> itemList = new ArrayList<>();
            itemList.add(item.getName());
            itemList.add(Double.toString(item.getPrice()));
            itemList.add(Integer.toString(item.getStock()));
            itemList.add(Integer.toString(item.getSold()));
            results.add(itemList);
        }

        try { excel.write(file, results); }
        catch(BiffException | IOException | WriteException e) {
            throw new IOException("Failed writing XLS-file.");
        }
    };

    protected abstract V makeObject(String[] tokens);
    protected abstract K getKey(String[] tokens);
}