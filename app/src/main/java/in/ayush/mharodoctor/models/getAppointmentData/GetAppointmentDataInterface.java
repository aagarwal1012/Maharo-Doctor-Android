package in.ayush.mharodoctor.models.getAppointmentData;

import in.ayush.mharodoctor.models.getAppointmentData.Output.GetAppointmentDataOutput;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface GetAppointmentDataInterface {

    @GET("get_appointment_data")
    Call<GetAppointmentDataOutput> getResult(@Query("id") String appointmentId);

}
