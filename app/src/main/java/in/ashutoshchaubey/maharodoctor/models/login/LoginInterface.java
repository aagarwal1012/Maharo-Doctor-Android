package in.ashutoshchaubey.maharodoctor.models.login;

import in.ashutoshchaubey.maharodoctor.models.login.Output.OutputLogin;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface LoginInterface {

    @POST("login")
    Call<OutputLogin> getResult(@Query("uid") String uid, @Query("password") String passsword);

}
