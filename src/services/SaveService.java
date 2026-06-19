package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import models.Cat;
import models.Dog;
import models.Dragon;
import models.Pet;

public class SaveService {
    private static final String SAVE_FILE = "pet_save.txt";

    public void savePet(Pet pet) {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write(pet.toSaveString());
            System.out.println("Game saved successfully.");
        } catch (IOException error) {
            System.out.println("Unable to save game.");
        }
    }

    public Pet loadPet() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line = reader.readLine();

            if (line == null) {
                return null;
            }

            String[] data = line.split(",");

            String type = data[0];
            String name = data[1];

            Pet pet;

            if (type.equals("Dog")) {
                pet = new Dog(name);
            } else if (type.equals("Cat")) {
                pet = new Cat(name);
            } else if (type.equals("Dragon")) {
                pet = new Dragon(name);
            } else {
                return null;
            }

            pet.loadStats(
                Integer.parseInt(data[2]),
                Integer.parseInt(data[3]),
                Integer.parseInt(data[4]),
                Integer.parseInt(data[5]),
                Integer.parseInt(data[6]),
                Integer.parseInt(data[7]),
                Integer.parseInt(data[8])
            );

            System.out.println("Game loaded successfully.");
            return pet;

        } catch (IOException error) {
            System.out.println("No saved game found.");
            return null;
        }
    }
}
