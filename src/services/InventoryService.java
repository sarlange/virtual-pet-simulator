package services;

import java.util.ArrayList;
import models.Item;

public class InventoryService {
    private ArrayList<Item> items;

    public InventoryService() {
        items = new ArrayList<>();
        seedStarterItems();
    }

    private void seedStarterItems() {
        items.add(new Item("Apple", "Reduces hunger", 15));
        items.add(new Item("Toy Ball", "Increases happiness", 10));
        items.add(new Item("Energy Biscuit", "Restores energy", 20));
        items.add(new Item("Health Potion", "Restores health", 25));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void showInventory() {
        System.out.println("\n--- Inventory ---");

        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.print((i + 1) + ". ");
            items.get(i).displayItem();
        }
    }

    public Item useItem(int index) {
        if (index < 0 || index >= items.size()) {
            System.out.println("Invalid item selection.");
            return null;
        }

        return items.remove(index);
    }

    public boolean hasItems() {
        return !items.isEmpty();
    }
}
