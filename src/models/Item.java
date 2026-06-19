package models;

public class Item {
    private String name;
    private String effect;
    private int value;

    public Item(String name, String effect, int value) {
        this.name = name;
        this.effect = effect;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public int getValue() {
        return value;
    }

    public void displayItem() {
        System.out.println(name + " | Effect: " + effect + " | Value: " + value);
    }
}
