package store.client;

import store.interfaces.Description;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class User implements Description {
    private static int user_num;
    private final int userID;
    private String name;
    private UserData userData;
    private String userDescription;
    private final String uniqueUserCode;
    private static final ArrayList<String> codes = new ArrayList<>();

    public User(String name, UserData userData) throws Exception {
        user_num++;
        this.userID = user_num;
        this.name = name;
        this.userData = userData;
        this.uniqueUserCode = generateUniqueCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getUniqueUserCode() {
        return uniqueUserCode;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getUsers() {
        return user_num;
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

