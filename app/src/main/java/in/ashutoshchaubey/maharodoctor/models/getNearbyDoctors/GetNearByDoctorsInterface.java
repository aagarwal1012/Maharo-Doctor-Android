package in.ashutoshchaubey.maharodoctor.models.getNearbyDoctors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetNearByDoctorsInterface {

    @GET("get_nearby_doctors")
    Call<GetNearByDoctorsOutput> getResult(@Query("euid") String euid);


}
