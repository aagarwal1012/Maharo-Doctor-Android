package in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetSingleAppointmentDataInterface {

    @GET("get_appointment_data")
    Call<GetSingleAppointmentDataOutput> getResult(@Query("euid") String euid, @Query("id") String id);

}
