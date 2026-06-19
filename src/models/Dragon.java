package models;

public class Dragon extends Pet {

    public Dragon(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Dragon";
    }

    @Override
    public void train() {
        System.out.println(name + " practices breathing fire!");
        super.train();
        gainExperience(10);
    }
}
