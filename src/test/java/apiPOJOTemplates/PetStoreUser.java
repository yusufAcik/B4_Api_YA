package apiPOJOTemplates;

public class PetStoreUser {
    /**
     * {
     *   "id": "9223372036854762000",
     *   "username": "jack",
     *   "firstName": "jackie",
     *   "lastName": "jo",
     *   "email": "jack@gmail.com",
     *   "password": "test1234",
     *   "phone": "5554442211",
     *   "userStatus": 0
     * }
     */

    private double id;

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private double userStatus;

    public double getId() {
        return id;
    }

    public void setId(double  id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(double userStatus) {
        this.userStatus = userStatus;
    }

    public PetStoreUser(double id, String userName, String firstName, String lastName, String email, String password, String phone, double userStatus) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "PetStoreUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}
