package store.interfaces;

/**
 * Represents an interface for providing descriptions.
 * Implementing classes should define methods to get and set descriptions.
 */
public interface Description {
    /**
     * Gets the description.
     *
     * @return The description.
     */
    String getDescription();

    /**
     * Sets the description.
     *
     * @param description The description to set.
     */
    void setDescription(String description);
}
