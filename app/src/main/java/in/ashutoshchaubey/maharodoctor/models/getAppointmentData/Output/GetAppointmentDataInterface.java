package in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetAppointmentDataInterface {

    @GET("get_user_appointments")
    Call<GetAppointmentDataOutput> getResult(@Query("euid") String euid);

}
