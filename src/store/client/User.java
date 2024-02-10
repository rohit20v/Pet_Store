package store.client;

import store.interfaces.Description;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Represents a User with various variables/attributes.
 * Implements the interface {@link Description}.
 * @author Rohit Verma
 */
public class User implements Description {
    /**
     * Class variables/attributes and methods
     */
    private static int user_num;
    private final int userID;
    private String name;
    private UserData userData;
    private String userDescription;
    private final String uniqueUserCode;
    private static final ArrayList<String> codes = new ArrayList<>();

    /**
     * Constructs a User object with various variables/attributes.
     *
     * @param name     User's name.
     * @param userData User's data: phone number and email address.'
     * @throws Exception If an error occurs during initialization.
     */
    public User(String name, UserData userData) throws Exception {
        user_num++;
        this.userID = user_num;
        this.name = name;
        this.userData = userData;
        this.uniqueUserCode = generateUniqueCode();
    }

    /**
     * Creates a new `User` object.
     * This constructor increments the user count, assigns a unique user ID,
     * and generates a unique user code.
     */
    public User(){
        user_num++;
        this.userID = user_num;
        this.uniqueUserCode = generateUniqueCode();
    }

    /**
     * Checks if this `UserData` object is equal to another object.
     *
     * @param obj The object to compare with.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Returns a clone of this `UserData` object.
     *
     * @return A cloned instance of this object.
     * @throws CloneNotSupportedException If cloning is not supported.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Gets the user's unique code.
     *
     * @return The unique user code.
     */
    public String getUniqueUserCode() {
        return uniqueUserCode;
    }

    /**
     * Gets the user's data: phone number and email from {@link UserData}.
     *
     * @return The user's data.
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     * Gets the user's name.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user's ID.
     *
     * @return The ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets the user's description.
     *
     * @return The description.
     */
    @Override
    public String getDescription() {
        return Objects.requireNonNullElse(userDescription, "None");
    }

    /**
     * Sets the user's description.
     *
     * @param description The description to set.
     */
    @Override
    public void setDescription(String description) {
        this.userDescription = description;
    }

    /**
     * Generates a unique alphanumeric code consisting of six characters.
     * The code is randomly created from a set of digits (0-9) and lowercase/uppercase letters.
     * It ensures that the generated code is not already present in the existing collection.
     *
     * @return A unique six-character code.
     */
    private String generateUniqueCode() {
        Random random = new Random();
        String code = "";
        String[] letters = "1234567890qwertyuiopasdfghklzxcvbnmllkQWERTYUIOPASDFGHJKLZXCVBNM/*-#@_".split("");
        while (true) {
            for (int i = 0; i < 6; i++) {
                code += letters[random.nextInt(letters.length)];
            }
            if (!codes.contains(code)) {
                codes.add(code);
                break;
            }
        }
        return code;
    }

    /**
     * Returns a string representation of this User object.
     *
     * @return A formatted string containing User ID, name, phone number, email, unique code (password) and description(if empty contains None).
     */
    @Override
    public String toString() {
        return "User " +
               "ID='" + getUserID() + '\'' +
               ", Name='" + name + '\'' +
               ", User Data=" + userData +
               ", Unique Code=" + uniqueUserCode +
               ", Description=" + getDescription();
    }
}

