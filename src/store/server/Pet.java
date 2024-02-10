package store.server;

import store.interfaces.Description;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Pet in the store.
 * Extends the Animal class and implements the interface {@link Description} and Cloneable.
 * @author Rohit Verma
 */
public class Pet extends Animal implements Description, Cloneable {
    /**
     * Class variables/attributes and methods
     */
    private final int petID;
    private static int pet_num;
    private String description;
    private boolean canSwim;
    private double price;

    /**
     * Constructs a new Pet object with the given attributes.
     *
     * @param name   the name of the pet
     * @param breed  the breed of the pet
     * @param age    the age of the pet
     * @param weight the weight of the pet
     * @param height the height of the pet
     * @param price  the price of the pet
     * @throws Exception if an error occurs during compilation or runtime
     */
    public Pet(String name, String breed, int age, float weight, int height, double price) throws Exception {
        super(name, breed, age, weight, height);
        this.price = price;
        Pet.pet_num += 1;
        this.petID = pet_num;
    }


    /**
     * Creates a new `Pet` object with the attributes inherited from the parent class.
     *
     * @param name    The name of the pet.
     * @param breed   The breed of the pet.
     * @param age     The age of the pet.
     * @param weight  The weight of the pet (in kilograms).
     * @param height  The height of the pet (in centimeters).
     * @throws Exception If any exceptional condition occurs during initialization.
     */
    public Pet(String name, String breed, int age, float weight, int height) throws Exception{
        super(name, breed, age, weight, height);
        Pet.pet_num += 1;
        this.petID = pet_num;
    }

    /**
     * Returns a clone of this `Pet` object.
     *
     * @return A cloned Pet object.
     * @throws CloneNotSupportedException If cloning is not supported.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Checks if this `Pet` object is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pet pet = (Pet) o;
        return petID == pet.petID && canSwim == pet.canSwim && Double.compare(price, pet.price) == 0 && Objects.equals(description, pet.description);
    }

    /**
     * Retrieves the total number of pets.
     *
     * @return The total number of pets.
     */
    public static int getPet_num() {
        return pet_num;
    }

    /**
     * Sets the pet's description.
     *
     * @param description The description of the pet.
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the pet's description.
     *
     * @return The pet's description (or "None" if not set).
     */
    @Override
    public String getDescription() {
        if (this.description == null) return "None";
        else return description;
    }


    /**
     * Increments the total number of pets.
     * This method is used to update the count of pets.
     */
    public static void setPet_num() {
        Pet.pet_num += 1;
    }

    /**
     * Sets whether the pet can swim.
     *
     * @param canSwim "YES" if the pet can swim, "NO" otherwise.
     */
    @Override
    public void setSwim(String canSwim) {
        if (canSwim.equalsIgnoreCase("NO"))
            this.canSwim = false;
        else if (canSwim.equalsIgnoreCase("YES"))
            this.canSwim = true;
    }

    /**
     * Checks if the pet can swim.
     *
     * @return true if the pet can swim, false otherwise.
     */
    @Override
    public boolean isSwim() {
        return canSwim;
    }

    /**
     * Gets the price of the pet.
     *
     * @return The pet's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the pet.
     *
     * @param price The new price for the pet.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Generates a string representation of the pet.
     *
     * @return A formatted string containing pet details.
     */
    @Override
    public String toString() {
        return "Pet ID= " + getPetID() + ", " +
               super.toString() +
               ", Description='" + getDescription() + '\'' +
               ", Price=" + price +
               ", Can swim=" + canSwim;
    }

    /**
     * Decrements the total number of pets when a pet is removed.
     *
     * @param p  The list of pets.
     * @param id The ID of the pet to remove.
     */
    public void pet_num_manager(ArrayList<Pet> p, int id) {
        if (p.removeIf(o -> o.getPetID() == id)) {
            Pet.pet_num -= 1;
        }
    }

    /**
     * Gets the ID of the pet.
     *
     * @return The pet's ID.
     */
    public int getPetID() {
        return petID;
    }
}
