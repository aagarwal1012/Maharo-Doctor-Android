package in.ashutoshchaubey.maharodoctor.models.signUp.Input;

public class SignUpInput {

    private String uid;
    private String password;
    private Boolean is_doctor = false;
    private String aadhaarNumber;
    private String mobile;
    private String email;
    private String name;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public SignUpInput(String uid, String password, String aadhaarNumber, String mobile, String email, String name) {

        this.uid = uid;
        this.password = password;
        this.aadhaarNumber = aadhaarNumber;
        this.mobile = mobile;
        this.email = email;
        this.name = name;
    }
}
