package a3101020604.airline_projecta3.Model;

/**
 * Created by justinfrasca on 2017-12-17.
 */

public class User {

    private int id;
    private String Username;
    private String Address;
    private String password;
    private String fullName;
    private String PhoneNum;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String name) {
        this.Username = Username;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String Password) {
        this.password = Password;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String FullName) {
        this.fullName = FullName;
    }
    public String getPhoneNum() {
        return PhoneNum;
    }
    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }
}
