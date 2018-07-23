package in.ashutoshchaubey.maharodoctor.models.logout;

import in.ashutoshchaubey.maharodoctor.models.logout.Output.LogoutOutput;
import retrofit.http.POST;
import retrofit2.Call;

public interface LogoutInterface {

    @POST("logout")
    Call<LogoutOutput> getResult();

}
