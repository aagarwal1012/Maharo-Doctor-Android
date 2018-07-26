package in.ayush.mharodoctor.models.requestAppointment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestAppointmentInterface {

    @GET("request")
    Call<RequestAppointmentOutput> getResult(@Query("euid") String euid, @Query("doctor") String doctorId, @Query("date") String date, @Query("uid") String uid);

}
