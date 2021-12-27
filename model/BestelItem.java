package model;

import java.util.Map;
import java.util.TreeMap;

public abstract class BestelItem extends Bestellijn {
    protected Bestellijn base;

    public BestelItem(String itemName, Bestellijn base) {
        super(itemName);
        this.base = base;
    }

    public double calculatePrice() { return this.item.getPrice() + this.base.calculatePrice(); }

    public String getFoundation() { return this.base.getFoundation(); }

    public String getDescription() {
        String description = this.name;
        String baseDescription = this.base.getDescription();

        if (!baseDescription.isEmpty()) { description = baseDescription + ", " + description; }
        return  description;
    }

    protected TreeMap<String, Integer> getStack() {
        TreeMap<String, Integer> stack = this.base.getStack();
        int amount = (stack.containsKey(this.name)) ? stack.get(this.name) + 1 : 1;
        stack.put(this.name, amount);
        return stack;
    }

    public String getSummary() {
        String summary = this.getFoundation();

        TreeMap<String, Integer> stack = this.getStack();
        if (stack.size() > 0) { summary += ": "; }

        int counter = 1;
        for(Map.Entry entry : stack.entrySet()) {
            String item = (String) entry.getKey();
            int amount = (Integer) entry.getValue();

            if (amount > 1) { summary += amount + " x "; }
            summary += item;
            if (counter < stack.size()) { summary += ", "; }
            counter++;
        }

        return summary;
    }

    public void clear() {
        super.clear();
        this.base.clear();
    }
}
