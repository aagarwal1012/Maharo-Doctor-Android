package in.ashutoshchaubey.maharodoctor.models.verifyAccount.Input;

public class VerifyAccountInput {

    private String uid;
    private String otp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public VerifyAccountInput(String uid, String otp) {

        this.uid = uid;
        this.otp = otp;
    }
}
