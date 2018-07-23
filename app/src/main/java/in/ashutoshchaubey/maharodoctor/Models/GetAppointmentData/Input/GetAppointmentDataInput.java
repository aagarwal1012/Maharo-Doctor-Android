package in.ashutoshchaubey.maharodoctor.Models.GetAppointmentData.Input;

public class GetAppointmentDataInput {
    String appointmentId;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public GetAppointmentDataInput(String appointmentId) {

        this.appointmentId = appointmentId;
    }
}
