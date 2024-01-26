public class UserData {
    private int phoneNumber;
    private String email;

    public UserData(int phoneNumber, String email) throws Exception {
        setPhoneNumber(phoneNumber);
        setEmail(email);

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
