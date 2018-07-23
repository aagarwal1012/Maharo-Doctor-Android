package in.ashutoshchaubey.maharodoctor.models.verifyAccount;

import in.ashutoshchaubey.maharodoctor.models.verifyAccount.Output.VerifyAccountOutput;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface VerifyAccountInterface {

    @POST("verify_account")
    Call<VerifyAccountOutput> getResult(
            @Query("uid") String uid,
            @Query("otp") String otp
            );

}
