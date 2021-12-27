package model;

import java.io.*;
import java.util.Properties;

public class Settings {
    private static Settings instance;
    private final Properties properties;
    private final String filename = "src/bestanden/settings.ini";

    public static Settings getInstance() {
        if (instance == null) { instance = new Settings(); }
        return instance;
    }

    private Settings() {
        this.properties = new Properties();
        try {
            InputStream file = new FileInputStream(this.filename);
            this.properties.load(file);
            file.close();
        }
        catch(IOException e) {
            System.out.println("Problem loading settings.ini.");
            System.exit(500);
        }
    }

    public String getDefaultKorting() { return this.properties.getProperty("korting", "Geen korting"); }
    public void setDefaultKorting(String korting) {
        if (korting == null) { throw new IllegalArgumentException("Default korting value may not be empty"); }
        properties.setProperty("korting", korting);
    }

    public String getDatasource() { return this.properties.getProperty("datasource", "text"); }
    public void setDatasource(String datasource) {
        if (datasource == null) { throw new IllegalArgumentException("Datasource value may not be empty"); }
        properties.setProperty("datasource", datasource);
    }

    public void save() {
        try {
            OutputStream file = new FileOutputStream(this.filename);
            this.properties.store(file, null);
            file.close();
        }
        catch(IOException e) {
            System.out.println("Problem writing to settings.ini.");
            System.exit(500);
        }
    }
}
