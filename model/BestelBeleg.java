package model;

public class BestelBeleg extends BestelItem {
    public BestelBeleg(String belegName, Bestellijn base) { super(belegName, base); }

    public Item getItemFromDb() { return this.bestelFacade.getBeleg(this.name); }

    public void setItemToDb(Item beleg) { this.bestelFacade.updateBeleg(this.name, (Beleg) beleg); }

    public Bestellijn copy() {
        Bestellijn baseCopy = this.base.copy();
        Bestellijn copy = new BestelBeleg(this.name, baseCopy);
        return copy;
    }
}
