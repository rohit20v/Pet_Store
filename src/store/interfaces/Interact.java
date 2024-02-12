package store.interfaces;

/**
 * The Interact interface represents the ability to interact with a pet.
 * It defines methods for petting, feeding, and playing with a pet.
 */
public interface Interact {
    /**
     * Pet the pet.
     */
    void pet();

    /**
     * Feed the pet
     */
    void feed();

    /**
     * Play with the pet
     */
    void play();
}
