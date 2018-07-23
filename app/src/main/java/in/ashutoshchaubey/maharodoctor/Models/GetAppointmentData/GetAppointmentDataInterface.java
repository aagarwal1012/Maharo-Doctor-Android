package in.ashutoshchaubey.maharodoctor.Models.GetAppointmentData;

import in.ashutoshchaubey.maharodoctor.Models.GetAppointmentData.Output.GetAppointmentDataOutput;
import in.ashutoshchaubey.maharodoctor.Models.Login.Output.OutputLogin;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface GetAppointmentDataInterface {

    @POST("get_appointment_data")
    Call<GetAppointmentDataOutput> getResult(@Query("id") String appointmentId);

}
