package in.ashutoshchaubey.maharodoctor.models.login.Output;

public class OutputLogin {

    String status;
    String message;
    Boolean is_doctor;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OutputLogin(String status, String message, Boolean is_doctor, String token) {

        this.status = status;
        this.message = message;
        this.is_doctor = is_doctor;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIs_doctor() {
        return is_doctor;
    }

    public void setIs_doctor(Boolean is_doctor) {
        this.is_doctor = is_doctor;
    }
}
