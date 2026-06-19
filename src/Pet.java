package models;

public abstract class Pet {
    protected String name;
    protected int hunger;
    protected int happiness;
    protected int energy;
    protected int health;
    protected int age;
    protected int level;
    protected int experience;

    public Pet(String name) {
        this.name = name;
        this.hunger = 50;
        this.happiness = 50;
        this.energy = 50;
        this.health = 100;
        this.age = 0;
        this.level = 1;
        this.experience = 0;
    }

    public abstract String getType();

    public void feed() {
        hunger -= 20;
        happiness += 5;
        gainExperience(5);
        normalizeStats();
    }

    public void play() {
        happiness += 20;
        energy -= 15;
        hunger += 10;
        gainExperience(10);
        normalizeStats();
    }

    public void sleep() {
        energy += 30;
        hunger += 5;
        health += 10;
        gainExperience(5);
        normalizeStats();
    }

    public void train() {
        energy -= 20;
        hunger += 15;
        happiness += 10;
        gainExperience(20);
        normalizeStats();
    }

    public void passTime() {
        age++;
        hunger += 10;
        happiness -= 5;
        energy -= 5;

        if (hunger > 80) {
            health -= 10;
        }

        if (energy < 20) {
            health -= 5;
        }

        if (happiness < 20) {
            health -= 5;
        }

        normalizeStats();
    }

    public void gainExperience(int amount) {
        experience += amount;

        if (experience >= level * 50) {
            experience = 0;
            level++;
            health += 10;
            happiness += 10;
            System.out.println(name + " leveled up to level " + level + "!");
        }

        normalizeStats();
    }

    protected void normalizeStats() {
        hunger = clamp(hunger);
        happiness = clamp(happiness);
        energy = clamp(energy);
        health = clamp(health);
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(100, value));
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStatus() {
        System.out.println("\n--- Pet Status ---");
        System.out.println("Name: " + name);
        System.out.println("Type: " + getType());
        System.out.println("Age: " + age + " days");
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience);
        System.out.println("Hunger: " + hunger);
        System.out.println("Happiness: " + happiness);
        System.out.println("Energy: " + energy);
        System.out.println("Health: " + health);
    }

    public String toSaveString() {
        return getType() + "," + name + "," + hunger + "," + happiness + "," + energy + "," + health + "," + age + "," + level + "," + experience;
    }

    public void loadStats(int hunger, int happiness, int energy, int health, int age, int level, int experience) {
        this.hunger = hunger;
        this.happiness = happiness;
        this.energy = energy;
        this.health = health;
        this.age = age;
        this.level = level;
        this.experience = experience;
        normalizeStats();
    }

    public String getName() {
        return name;
    }
}
