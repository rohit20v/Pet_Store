package store.server;

import store.interfaces.Animal_Preferences;

import java.util.Objects;

/**
 * @author Rohit Verma
 * Represents an abstract animal with various properties.
 * Implements interface {@link Animal_Preferences}.
 */
public abstract class Animal implements Animal_Preferences {
    /**
     * Class variables and methods
     */
    private final String type;
    private final String breed;
    private final int age;
    private final float weight; // in kilograms
    private final int height; // in centimeters
    private String favFood;

    /**
     * Creates a new Animal object with the given attributes.
     *
     * @param type   The type of the animal.
     * @param breed  The breed of the animal.
     * @param age    The age of the animal.
     * @param weight The weight of the animal.
     * @param height The height of the animal.
     * @throws Exception If any exceptional condition occurs during initialization.
     */
    public Animal(String type, String breed, int age, float weight, int height) throws Exception {
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Checks if this `Animal` object is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Float.compare(weight, animal.weight) == 0 && height == animal.height && Objects.equals(type, animal.type) && Objects.equals(breed, animal.breed) && Objects.equals(favFood, animal.favFood);
    }

    /**
     * Returns a clone of this `Animal` object.
     *
     * @return A cloned Animal object.
     * @throws CloneNotSupportedException If cloning is not supported.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * This method is used to get the type of the object.
     *
     * @return String This returns the type of the object.
     */
    public String getType() {
        return type;
    }

    /**
     * This method is used to get the breed of the object.
     *
     * @return String This returns the breed of the object.
     */
    public String getBreed() {
        return breed;
    }

    /**
     * This method is used to get the age of the object.
     *
     * @return String This returns the age of the object.
     */
    public int getAge() {
        return age;
    }

    /**
     * This method is used to get the weight of the object.
     *
     * @return String This returns the weight of the object.
     */
    public float getWeight() {
        return weight;
    }

    /**
     * This method is used to get the weight of the object.
     *
     * @return String This returns the weight of the object.
     */
    public int getHeight() {
        return height;
    }

    /**
     * An abstract method that sets the favorite food of the Animal.
     *
     * @param favoriteFood The object's favorite food to set.
     */
    @Override
    public void setFavoriteFood(String favoriteFood) {
        this.favFood = favoriteFood;
    }

    /**
     * This method is used to get the favorite food of the object.
     *
     * @return String This returns the favorite food of the object, or "None" if not set.
     */
    @Override
    public String getFavoriteFood() {
        if (favFood == null)
            return "None";
        else return favFood;
    }

    /**
     * An abstract method that is used to check if the object can swim.
     *
     * @return boolean returns true if the object can swim, false otherwise.
     */
    public abstract boolean isSwim();

    /**
     * This method is used to set if the object can swim.
     *
     * @param canSwim This is a string representation of whether the object can swim or not.
     * @throws Exception If the input is not valid.
     */
    public abstract void setSwim(String canSwim) throws Exception;

    /**
     * This method is used to get a string representation of the object.
     *
     * @return String This returns a string representation of the object.
     */
    @Override
    public String toString() {
        return
                "Type= " + type +
                ", Breed= " + breed +
                ", Age= " + age +
                ", Weight= " + weight + "kg" +
                ", Height= " + height + "cm" + "\n" +
                "Favourite food= " + getFavoriteFood();
    }

}
