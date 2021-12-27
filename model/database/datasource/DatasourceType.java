package model.database.datasource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public enum DatasourceType {
    TXT( "Text"),
    XLS("Excel");

    private final String name;

    private DatasourceType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public static DatasourceType findByName(String name){
        for (DatasourceType type : values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }

    public static ObservableList<String> getAllAsList() {
        ArrayList<String> types = new ArrayList<>();
        for(DatasourceType type : DatasourceType.values()) {
            types.add(type.getName());
        }
        return FXCollections.observableArrayList(types);
    }
}
