package in.ashutoshchaubey.maharodoctor.models.login.Input;

public class LoginInput {

    String uid;
    String password;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginInput(String uid, String password) {

        this.uid = uid;
        this.password = password;
    }
}
