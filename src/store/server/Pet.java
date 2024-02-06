package store.server;

import store.interfaces.Description;

import java.util.ArrayList;

public class Pet extends Animal implements Description, Cloneable{
    private final int petID;
    private static int pet_num;
    private String description;
    private boolean canSwim;
    private double price;

    public Pet(String name, String breed, int age, float weight, int height, double price) throws Exception {
        super(name, breed, age, weight, height);
        this.price = price;
        Pet.pet_num += 1;
        this.petID = pet_num;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static int getPet_num() {
        return pet_num;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        if(this.description == null) return "None";
        else return description;
    }

    @Override
    public void setSwim(String canSwim) {
        if (canSwim.equalsIgnoreCase("NO"))
            this.canSwim = false;
        else if (canSwim.equalsIgnoreCase("YES"))
            this.canSwim = true;
    }

    @Override
    public boolean isSwim() {
        return canSwim;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pet ID= " + getPetID() + ", " +
               super.toString() +
               ", Description='" + getDescription() + '\'' +
               ", Price=" + price +
               ", Can swim=" + canSwim;
    }

    public void pet_num_manager(ArrayList<Pet> p, int id) {
        if(p.removeIf(o -> o.getPetID() == id)){
            Pet.pet_num -= 1;
        }
    }
    public int getPetID() {
        return petID;
    }
}
