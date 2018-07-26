package in.ayush.mharodoctor.models.getName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetNameInterface {

    @GET("get_user_name")
    Call<GetNameOutput> getResult(@Query("uid") String uid);

}
