package in.ashutoshchaubey.maharodoctor.models.signUp;

import in.ashutoshchaubey.maharodoctor.models.signUp.Output.SignUpOutput;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit2.Call;

public interface SignUpInterface {

    @POST("register")
    Call<SignUpOutput> getResult(
            @Query("uid") String uid,
            @Query("password") String password,
            @Query("is_doctor") int is_doctor,
            @Query("mobile") String mobile,
            @Query("email") String email,
            @Query("name") String name,
            @Query("aadhaar") String aadhaarNumber
            );

}
