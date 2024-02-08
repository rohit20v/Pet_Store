package store.client;

import java.util.Objects;

/**
 * Represents user data containing phone number and email.
 *  This class provides methods to set and retrieve user information.
 * @author Rohit Verma
 *
 */
public class UserData {
    private int phoneNumber;
    private String email;

    /**
     * Constructs a `UserData` object with the specified phone number and email.
     *
     * @param phoneNumber The user's phone number.
     * @param email       The user's email address.
     * @throws Exception If an error occurs during initialization.
     */
    public UserData(int phoneNumber, String email) throws Exception {
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    /**
     * Checks if this `UserData` object is equal to another object.
     *
     * @param o The object to compare with.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return phoneNumber == userData.phoneNumber && Objects.equals(email, userData.email);
    }

    /**
     * Returns a clone of this `UserData` object.
     *
     * @return A cloned instance of this object.
     * @throws CloneNotSupportedException If cloning is not supported.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Gets the user's phone number.
     *
     * @return The phone number.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phoneNumber The phone number to set.
     * @throws Exception If an error occurs during validation.
     */
    public void setPhoneNumber(int phoneNumber) throws Exception {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of this `UserData` object.
     *
     * @return A formatted string containing phone number and email.
     */
    @Override
    public String toString() {
        return "{" +
               "PhoneNumber=" + phoneNumber +
               ", Email='" + email + '\'' +
               '}';
    }
}
