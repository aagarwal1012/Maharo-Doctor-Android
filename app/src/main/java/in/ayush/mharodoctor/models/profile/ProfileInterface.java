package in.ayush.mharodoctor.models.profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileInterface {

    @GET("get_user_contact")
    Call<ProfileOutput> getResult(@Query("uid") String uid, @Query("euid") String euid);


}
