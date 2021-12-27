package model.database.datasource;

import java.io.IOException;
import java.util.TreeMap;

public interface Datasource<K,V> {
    TreeMap<K, V> load() throws IOException;
    void save(TreeMap<K,V> items) throws IOException;
}
