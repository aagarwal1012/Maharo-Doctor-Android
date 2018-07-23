package in.ashutoshchaubey.maharodoctor.Models.Login;

import in.ashutoshchaubey.maharodoctor.Models.Login.Output.OutputLogin;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface LoginInterface {

    @POST("login")
    Call<OutputLogin> getResult(@Query("uid") String uid, @Query("password") String passsword);

}
