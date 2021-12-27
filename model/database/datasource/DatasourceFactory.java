package model.database.datasource;

public class DatasourceFactory {
    public static Datasource get(String datasource, String itemType) {
        Datasource instance = null;
        String packageName = "model.database.datasource.";
        String lowercaseItem = itemType.toLowerCase();
        String uppercaseFirstItem = itemType.substring(0, 1).toUpperCase() + itemType.toLowerCase().substring(1);
        datasource = datasource.substring(0, 1).toUpperCase() + datasource.toLowerCase().substring(1);
        String className = packageName + lowercaseItem + "." + uppercaseFirstItem + datasource;

        try {
            instance = (Datasource) Class.forName(className).getConstructor().newInstance();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(500);
        }
        return instance;
    }
}