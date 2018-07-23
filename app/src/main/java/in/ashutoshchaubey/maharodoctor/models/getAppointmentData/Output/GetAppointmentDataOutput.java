package in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output;

public class GetAppointmentDataOutput {

    String status;
    String message;
    //TODO : "DATA"


    public GetAppointmentDataOutput(String status, String message) {
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
