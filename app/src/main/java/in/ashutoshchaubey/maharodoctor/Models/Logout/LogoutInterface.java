package in.ashutoshchaubey.maharodoctor.Models.Logout;

import in.ashutoshchaubey.maharodoctor.Models.Logout.Output.LogoutOutput;
import retrofit.http.POST;
import retrofit2.Call;

public interface LogoutInterface {

    @POST("logout")
    Call<LogoutOutput> getResult();

}
