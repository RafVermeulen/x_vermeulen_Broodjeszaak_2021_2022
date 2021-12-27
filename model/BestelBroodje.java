package model;

public class BestelBroodje extends Bestellijn {
    public BestelBroodje(String broodjeName) { super(broodjeName); }

    public Item getItemFromDb() { return this.bestelFacade.getBroodje(this.name); }

    public void setItemToDb(Item broodje) { this.bestelFacade.updateBroodje(this.name, (Broodje) broodje); }

    public String getDescription() { return ""; }

    public Bestellijn copy() {
        Bestellijn copy = new BestelBroodje(this.name);
        return copy;
    }
}
