package model.database.datasource;

import model.Item;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public abstract class TextTemplate<K,V>{
    protected String filename;
    public final TreeMap<K,V> load() throws IOException {
        TreeMap<K,V> returnMap = new TreeMap<K,V>();

        File file = new File(this.filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(",");
                V element = makeObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    public final void save(TreeMap items) throws IOException {
        File file = new File(this.filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

        for (Object obj : items.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            Item item = (Item) entry.getValue();
            String line = item.getName() + "," + item.getPrice() + "," + item.getStock() + "," + item.getSold();
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    protected abstract V makeObject(String[] tokens);
    protected abstract K getKey(String[] tokens);
}