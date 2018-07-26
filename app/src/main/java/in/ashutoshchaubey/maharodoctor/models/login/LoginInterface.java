package in.ashutoshchaubey.maharodoctor.models.login;

import in.ashutoshchaubey.maharodoctor.models.login.Output.OutputLogin;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface LoginInterface {

    @GET("login")
    Call<OutputLogin> getResult(@Query("uid") String uid, @Query("password") String passsword);

}
