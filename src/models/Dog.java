package models;

public class Dog extends Pet {

    public Dog(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Dog";
    }

    @Override
    public void play() {
        System.out.println(name + " happily chases a ball!");
        super.play();
        happiness += 5;
        normalizeStats();
    }
}
