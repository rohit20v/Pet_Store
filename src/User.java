
import store.interfaces.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class User implements Description {
    private static int user_num;
    private final int userID;
    private String name;
    private UserData userData;
    private String userDescription;
    private final String uniqueUserCode;
    private final ArrayList<String> codes = new ArrayList<>();

    public User(String name, UserData userData) throws Exception {
        user_num++;
        this.userID = user_num;
        this.name = name;
        this.userData = userData;
        this.uniqueUserCode = generateUniqueCode();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
        if (userDescription.isEmpty()) {
            return "None";
        } else return userDescription;
    }

    @Override
    public void setDescription(String description) {
        this.userDescription = description;
    }

    private String generateUniqueCode() {
        Random random = new Random();
        String[] code = new String[5];
        String[] letters = "1234567890qwertyuiopasdfghklzxcvbnmllkQWERTYUIOPASDFGHJKLZXCVBNM/*-#@_".split("");
        while (true) {
            for (int i = 0; i < code.length; i++) {
                code[i] = letters[random.nextInt(letters.length)];
            }
            if (!codes.contains(Arrays.toString(code))) {
                codes.add(Arrays.toString(code));
                break;
            }
        }
        return Arrays.toString(code);
    }

    private void deleteUser(){

    }

    @Override
    public String toString() {
        return "Store.Interfaces.User " +
               "ID='" + getUserID() + '\'' +
               ", Name='" + name + '\'' +
               ", Store.Interfaces.User Data=" + userData +
               ", Unique Code=" + uniqueUserCode +
               ", Description=" + getDescription();
    }
}

