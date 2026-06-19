package models;

public class Cat extends Pet {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Cat";
    }

    @Override
    public void sleep() {
        System.out.println(name + " curls up in a sunny spot.");
        super.sleep();
        energy += 5;
        normalizeStats();
    }
}
