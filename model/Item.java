package model;

public abstract class Item {
    protected String name;
    protected double price;
    protected int stock;
    protected int sold;

    public Item(String name, double price, int stock, int sold) throws IllegalArgumentException {
        this.setName(name);
        this.setPrice(price);
        this.setStock(stock);
        this.setSold(sold);
    }

    public void setName(String newType) {
        if (newType == null || newType.trim() == "") {
            throw new IllegalArgumentException("Name may not be empty");
        }
        this.name = newType;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double newPrice) {
        if (newPrice < 0) { throw new IllegalArgumentException("Price may not be lower than 0"); }
        this.price = newPrice;
    }

    public double getPrice() {
        return this.price;
    }

    public void setStock(int amount) {
        if (amount < 0) { throw new IllegalArgumentException("Stock amount may not be lower than 0"); }
        this.stock = amount;
    }

    public int getStock() {
        return this.stock;
    }

    public void setSold(int amount) {
        if (amount < 0) { throw new IllegalArgumentException("Sold amount may not be lower than 0"); }
        this.sold = amount;
    }

    public int getSold() {
        return this.sold;
    }
}
