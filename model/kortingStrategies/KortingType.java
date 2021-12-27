package model.kortingStrategies;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public enum KortingType {
    GEENKORTING ("Geen korting", "model.kortingStrategies.GeenKorting"),
    TIENPROCENT ("10% korting op ganse bestelling", "model.kortingStrategies.TienProcentKorting"),
    GOEDKOOPSTEGRATIS ("Goedkoopste broodje met beleg gratis", "model.kortingStrategies.GoedkoopsteGratisKorting");


    private final String name;
    private final String className;

    KortingType(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() { return this.name; }
    public String getClassName() { return this.className; }

    public static KortingType findByName(String name){
        for (KortingType type : values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        return null;
    }

    public static ObservableList<String> getAllAsList() {
        ArrayList<String> types = new ArrayList<>();
        for (KortingType type : KortingType.values()) {
            types.add(type.getName());
        }
        return FXCollections.observableArrayList(types);
    }
}
