package store.interfaces;

/**
 * Interface represents preferences related to animals
 * Implementing classes should provide methods to set and retrieve favorite food information.
 * @author Rohit Verma
 */
public interface Animal_Preferences {
    /**
     * Sets the favorite food for the animal.
     *
     * @param favoriteFood The favorite food to set.
     */
    void setFavoriteFood(String favoriteFood);

    /**
     * Retrieves the favorite food of the animal.
     *
     * @return The favorite food.
     */
    String getFavoriteFood();
}
