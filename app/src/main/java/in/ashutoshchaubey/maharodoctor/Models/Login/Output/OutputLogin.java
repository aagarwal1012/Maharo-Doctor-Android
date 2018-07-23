package in.ashutoshchaubey.maharodoctor.Models.Login.Output;

public class OutputLogin {

    String status;
    String message;
    Boolean is_doctor;

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
