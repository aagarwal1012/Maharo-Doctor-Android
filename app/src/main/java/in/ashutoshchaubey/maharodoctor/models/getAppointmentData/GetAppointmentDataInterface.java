package in.ashutoshchaubey.maharodoctor.models.getAppointmentData;

import in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output.GetAppointmentDataOutput;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface GetAppointmentDataInterface {

    @POST("get_appointment_data")
    Call<GetAppointmentDataOutput> getResult(@Query("id") String appointmentId);

}
