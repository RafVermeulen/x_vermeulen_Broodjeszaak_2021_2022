package model;

import model.database.BestelFacade;
import java.util.TreeMap;

public abstract class Bestellijn {
    protected BestelFacade bestelFacade;
    protected String name;
    protected Item item;

    public Bestellijn(String itemName) {
        this.bestelFacade = new BestelFacade();
        this.name = itemName;
        this.item = this.getItemFromDb();
        if (!this.sufficientStock(1)) {
            throw new IllegalArgumentException("Er is niet voldoende voorraad van " + this.name + ".");
        }
        this.adjustStock(1);
        this.adjustSold(1);
    }

    public abstract Item getItemFromDb();

    public abstract void setItemToDb(Item item);

    public abstract String getDescription();

    public double calculatePrice() { return this.item.getPrice(); }

    public String getName() { return this.name; }

    public String getFoundation() { return this.name; }

    public Item get() { return this.item; }

    public String getSummary() { return this.name; }

    public void adjustStock(int amount) {
        Item item = this.getItemFromDb();
        item.setStock(item.getStock() - amount);
        this.setItemToDb(item);
    }

    public void adjustSold(int amount) {
        Item item = this.getItemFromDb();
        item.setSold(item.getSold() + amount);
        this.setItemToDb(item);
    }

    public boolean sufficientStock(int amount) {
        Item item = this.getItemFromDb();
        return (amount <= item.getStock());
    }

    protected TreeMap<String, Integer> getStack() {
        TreeMap<String, Integer> stack = new TreeMap<>();
        return stack;
    }

    public void clear() {
        this.adjustStock(-1);
        this.adjustSold(-1);
    }

    public abstract Bestellijn copy();
}
