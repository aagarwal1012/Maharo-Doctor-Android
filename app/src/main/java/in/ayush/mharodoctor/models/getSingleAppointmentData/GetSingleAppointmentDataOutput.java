package in.ayush.mharodoctor.models.getSingleAppointmentData;


public class GetSingleAppointmentDataOutput {
    String status, message;
    SingleAppointmentData data;

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

    public SingleAppointmentData getData() {
        return data;
    }

    public void setData(SingleAppointmentData data) {
        this.data = data;
    }
}
