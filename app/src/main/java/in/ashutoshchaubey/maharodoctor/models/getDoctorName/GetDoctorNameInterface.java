package in.ashutoshchaubey.maharodoctor.models.getDoctorName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDoctorNameInterface {

    @GET("get_doctor_name")
    Call<GetDoctorNameOutput> getResult(@Query("uid") String uid);

}
