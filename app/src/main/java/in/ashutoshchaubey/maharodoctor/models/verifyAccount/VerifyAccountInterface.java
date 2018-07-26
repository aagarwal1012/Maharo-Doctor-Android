package in.ashutoshchaubey.maharodoctor.models.verifyAccount;

import in.ashutoshchaubey.maharodoctor.models.verifyAccount.Output.VerifyAccountOutput;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

public interface VerifyAccountInterface {

    @GET("verify_account")
    Call<VerifyAccountOutput> getResult(
            @Query("uid") String uid,
            @Query("otp") String otp
            );

}
