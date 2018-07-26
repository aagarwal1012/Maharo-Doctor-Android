package in.ayush.mharodoctor.models.logout;

import in.ayush.mharodoctor.models.logout.Output.LogoutOutput;
import retrofit2.http.GET;
import retrofit2.Call;

public interface LogoutInterface {

    @GET("logout")
    Call<LogoutOutput> getResult();

}
