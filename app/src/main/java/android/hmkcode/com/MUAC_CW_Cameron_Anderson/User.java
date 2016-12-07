package android.hmkcode.com.MUAC_CW_Cameron_Anderson;

/**
 * Created by Cammy on 03/12/16.
 */

public class User {

    // variables
    private int id;
    private String username;
    private String password;

    public User(){}

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    //getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password
                + "]";
    }


}
