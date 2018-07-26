package in.ayush.mharodoctor.models.emergency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PanicInterface {

    @GET("panic")
    Call<PanicOutput> getResult(@Query("euid") String euid, @Query("uid") String uid);

}
