
import store.interfaces.Animal_Preferences;

public abstract class Animal implements Animal_Preferences {

    private String type;
    private String breed;
    private int age;
    private float weight;
    private int height;
    private String favFood;

    public Animal(String type, String breed, int age, float weight, int height) throws Exception {
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setFavoriteFood(String favoriteFood) {
        this.favFood = favoriteFood;
    }

    @Override
    public String getFavoriteFood() {
        if (favFood == null)
            return "None";
        else return favFood;
    }

    public abstract boolean isSwim();

    public abstract void setSwim(String canSwim) throws Exception;

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
