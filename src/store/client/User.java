package store.client;

import store.interfaces.Description;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * @author Rohit Verma
 * Represents a User with various variables/attributes.
 * Implements the interface {@link Description}.
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
     * Constructs a User object with various variables/attributes.x
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

    public String getUniqueUserCode() {
        return uniqueUserCode;
    }

    public UserData getUserData() {
        return userData;
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String getDescription() {
        return Objects.requireNonNullElse(userDescription, "None");
    }

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

