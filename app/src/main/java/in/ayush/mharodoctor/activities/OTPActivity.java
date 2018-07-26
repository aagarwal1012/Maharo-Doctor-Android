package in.ayush.mharodoctor.activities;

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

import in.ayush.mharodoctor.R;
import in.ayush.mharodoctor.models.verifyAccount.Output.VerifyAccountOutput;
import in.ayush.mharodoctor.models.verifyAccount.VerifyAccountInterface;
import in.ayush.mharodoctor.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        try{
            username = getIntent().getExtras().getString(Constants.USER_ID);
        }
        catch (Exception e){
            Log.e("Error :", e.toString());
        }

        otp = (EditText) findViewById(R.id.otp_edit_text);

        confirmOtp = (Button) findViewById(R.id.confirm_otp_button);

        progressDialog = new ProgressDialog(OTPActivity.this);

        confirmOtp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_otp_button) {
            if (otp.getText() != null) {
                progressDialog.setTitle("Confirming OTP");
                progressDialog.setMessage("Loading ...");
                progressDialog.show();

                VerifyAccountInterface verifyAccountInterface = Constants.getRetrofit().create(VerifyAccountInterface.class);

                Call<VerifyAccountOutput> call = verifyAccountInterface.getResult(username, otp.getText().toString());

                call.enqueue(new Callback<VerifyAccountOutput>() {
                    @Override
                    public void onResponse(Call<VerifyAccountOutput> call, Response<VerifyAccountOutput> response) {
                        if (response.body().getStatus().equals("ok")) {
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
                                            Intent intent = new Intent(OTPActivity.this, LoginActivity.class);
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
