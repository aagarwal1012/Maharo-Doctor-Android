package in.ashutoshchaubey.maharodoctor.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.profile.ProfileInterface;
import in.ashutoshchaubey.maharodoctor.models.profile.ProfileOutput;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.EUID;
import static in.ashutoshchaubey.maharodoctor.Constants.IS_LOGGED_IN;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.USER_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView name, aadhaar, mobileNumber, district, email;

    ProgressDialog progressDialog;

    String uid, euid;

    Button logout;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = (TextView) findViewById(R.id.email_profile);
        name = (TextView) findViewById(R.id.name_profile);
        aadhaar = (TextView) findViewById(R.id.aadhaar_profile);
        mobileNumber = (TextView) findViewById(R.id.mobile_number_profile);
        district = (TextView) findViewById(R.id.district_profile);

        logout = (Button) findViewById(R.id.logout_profile);
        logout.setOnClickListener(this);

        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setTitle("Getting Profile");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        euid = sharedPreferences.getString(EUID, "euid");
        uid = sharedPreferences.getString(USER_ID, "uid");

        ProfileInterface profileInterface = getRetrofit().create(ProfileInterface.class);
        Call<ProfileOutput> call = profileInterface.getResult(uid, euid);
        call.enqueue(new Callback<ProfileOutput>() {
            @Override
            public void onResponse(Call<ProfileOutput> call, Response<ProfileOutput> response) {
                Log.d("Get Profile Data --> ", "Status : " + response.body().getStatus());

                if (response.body().getStatus().equals("ok")) {
                    ProfileOutput profileOutput = response.body();

                    name.setText(profileOutput.getContact().getName());
                    aadhaar.setText(profileOutput.getContact().getAadhar());
                    mobileNumber.setText(profileOutput.getContact().getMobile());
                    district.setText(profileOutput.getContact().getDistrict());
                    email.setText(profileOutput.getContact().getEmail());
                    progressDialog.dismiss();

                }

            }

            @Override
            public void onFailure(Call<ProfileOutput> call, Throwable t) {
                Log.e("Error : ", t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.logout_profile){
            sharedPreferences.edit().putBoolean(IS_LOGGED_IN, false).commit();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        }
    }
}
