package in.ashutoshchaubey.maharodoctor.Models.Logout.Output;

public class LogoutOutput {

    String status;
    String message;

    public LogoutOutput(String status, String message) {
        this.status = status;
        this.message = message;
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
}
