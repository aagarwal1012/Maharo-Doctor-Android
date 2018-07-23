package in.ashutoshchaubey.maharodoctor.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.verifyAccount.Output.VerifyAccountOutput;
import in.ashutoshchaubey.maharodoctor.models.verifyAccount.VerifyAccountInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static in.ashutoshchaubey.maharodoctor.Constants.API_URL;
import static in.ashutoshchaubey.maharodoctor.Constants.USER_ID;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    EditText otp;
    Button confirmOtp;

    String username;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        username = getIntent().getExtras().getString(USER_ID);

        otp = (EditText) findViewById(R.id.otp_edit_text);

        confirmOtp = (Button) findViewById(R.id.confirm_otp_button);

        progressDialog = new ProgressDialog(OTPActivity.this);

        confirmOtp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.otp_edit_text) {
            if (otp.getText() != null) {
                progressDialog.setTitle("Confirming OTP");
                progressDialog.setMessage("Loading ...");
                progressDialog.show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                VerifyAccountInterface verifyAccountInterface = retrofit.create(VerifyAccountInterface.class);

                Call<VerifyAccountOutput> call = verifyAccountInterface.getResult(username, otp.getText().toString());

                call.enqueue(new Callback<VerifyAccountOutput>() {
                    @Override
                    public void onResponse(Call<VerifyAccountOutput> call, Response<VerifyAccountOutput> response) {
                        if (response.body().getStatus() == "ok") {
                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(OTPActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                            } else {
                                builder = new AlertDialog.Builder(OTPActivity.this);
                            }
                            builder.setTitle("Sign up successful !")
                                    .setMessage("Please go to login page...")
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(OTPActivity.this, OTPActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        } else {
                            Toast.makeText(OTPActivity.this, response.body().getMessage(), Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<VerifyAccountOutput> call, Throwable t) {
                        Log.e("Error : ", t.toString());
                    }
                });
            } else {
                Toast.makeText(OTPActivity.this, "Enter OTP", Toast.LENGTH_LONG);
            }
        }
    }
}
