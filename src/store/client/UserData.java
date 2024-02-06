package store.client;

import java.util.Objects;

public class UserData {
    private int phoneNumber;
    private String email;

    public UserData(int phoneNumber, String email) throws Exception {
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return phoneNumber == userData.phoneNumber && Objects.equals(email, userData.email);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) throws Exception {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
               "PhoneNumber=" + phoneNumber +
               ", Email='" + email + '\'' +
               '}';
    }
}
