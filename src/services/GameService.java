package services;

import java.util.Random;

import models.Cat;
import models.Dog;
import models.Dragon;
import models.Item;
import models.Pet;
import utils.InputHelper;

public class GameService {
    private Pet pet;
    private InventoryService inventoryService;
    private SaveService saveService;
    private InputHelper inputHelper;
    private Random random;

    public GameService() {
        inventoryService = new InventoryService();
        saveService = new SaveService();
        inputHelper = new InputHelper();
        random = new Random();
    }

    public void start() {
        System.out.println("=== Virtual Pet Simulator ===");

        int choice = inputHelper.readInt(
            "\n1. New Game\n2. Load Game\nChoose an option: "
        );

        if (choice == 2) {
            pet = saveService.loadPet();
        }

        if (pet == null) {
            createPet();
        }

        gameLoop();
    }

    private void createPet() {
        String name = inputHelper.readString("Enter your pet's name: ");

        System.out.println("\nChoose pet type:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Dragon");

        int type = inputHelper.readInt("Choice: ");

        if (type == 1) {
            pet = new Dog(name);
        } else if (type == 2) {
            pet = new Cat(name);
        } else {
            pet = new Dragon(name);
        }

        System.out.println("\nYou adopted " + pet.getName() + "!");
    }

    private void gameLoop() {
        boolean running = true;

        while (running && pet.isAlive()) {
            showMenu();

            int choice = inputHelper.readInt("Choose an action: ");

            switch (choice) {
                case 1:
                    pet.feed();
                    System.out.println(pet.getName() + " enjoyed the meal.");
                    break;
                case 2:
                    pet.play();
                    break;
                case 3:
                    pet.sleep();
                    System.out.println(pet.getName() + " feels rested.");
                    break;
                case 4:
                    pet.train();
                    break;
                case 5:
                    useInventoryItem();
                    break;
                case 6:
                    pet.displayStatus();
                    break;
                case 7:
                    saveService.savePet(pet);
                    break;
                case 8:
                    running = false;
                    saveService.savePet(pet);
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            if (choice >= 1 && choice <= 5) {
                pet.passTime();
                triggerRandomEvent();
            }
        }

        if (!pet.isAlive()) {
            System.out.println("\n" + pet.getName() + " has become too unhealthy. Game over.");
        }
    }

    private void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Feed pet");
        System.out.println("2. Play with pet");
        System.out.println("3. Let pet sleep");
        System.out.println("4. Train pet");
        System.out.println("5. Use inventory item");
        System.out.println("6. Show pet status");
        System.out.println("7. Save game");
        System.out.println("8. Exit");
    }

    private void useInventoryItem() {
        inventoryService.showInventory();

        if (!inventoryService.hasItems()) {
            return;
        }

        int choice = inputHelper.readInt("Choose item number: ");
        Item item = inventoryService.useItem(choice - 1);

        if (item == null) {
            return;
        }

        System.out.println(pet.getName() + " used " + item.getName() + ".");
        pet.gainExperience(item.getValue());
    }

    private void triggerRandomEvent() {
        int eventChance = random.nextInt(100);

        if (eventChance < 20) {
            int eventType = random.nextInt(3);

            System.out.println("\n--- Random Event ---");

            if (eventType == 0) {
                System.out.println(pet.getName() + " found a snack!");
                inventoryService.addItem(new Item("Mystery Snack", "Bonus experience", 10));
            } else if (eventType == 1) {
                System.out.println(pet.getName() + " made a new friend!");
                pet.gainExperience(15);
            } else {
                System.out.println("A rainy day made " + pet.getName() + " sleepy.");
            }
        }
    }
}
