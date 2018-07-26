package in.ashutoshchaubey.maharodoctor.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.signUp.Output.SignUpOutput;
import in.ashutoshchaubey.maharodoctor.models.signUp.SignUpInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.DISTRICT_LIST;
import static in.ashutoshchaubey.maharodoctor.Constants.USER_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText username, name, email, aadhaarNumber, mobileNumber, password, retypePassword;
    Button signUpButton;

    Spinner district;

    String districtName = "Bikaner";

    ArrayAdapter<String> arrayAdapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RelativeLayout signInParent = (RelativeLayout) findViewById(R.id.sign_in_parent);
        signInParent.getBackground().setAlpha(128);

        username = (EditText) findViewById(R.id.username_edit_text_sign_up);
        name = (EditText) findViewById(R.id.name_edit_text_sign_up);
        email = (EditText) findViewById(R.id.email_address_edit_text_sign_up);
        aadhaarNumber = (EditText) findViewById(R.id.aadhaar_number_edit_text_sign_up);
        mobileNumber = (EditText) findViewById(R.id.mobile_number_edit_text_sign_up);
        password = (EditText) findViewById(R.id.password_edit_text_sign_up);
        retypePassword = (EditText) findViewById(R.id.retype_password_edit_text_sign_up);

        district = (Spinner) findViewById(R.id.district_name_spinner_sign_up);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, DISTRICT_LIST);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(arrayAdapter);
        district.setOnItemSelectedListener(this);

        signUpButton = (Button) findViewById(R.id.button_sign_up);

        progressDialog = new ProgressDialog(SignUpActivity.this);

        signUpButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_sign_up) {
            if (username.getText() != null && name.getText() != null && email.getText() != null && aadhaarNumber.getText() != null && mobileNumber.getText() != null && password.getText() != null && retypePassword.getText() != null) {
                if (password.getText().toString().equals(retypePassword.getText().toString())) {
                    progressDialog.setTitle("Logging In");
                    progressDialog.setMessage("Loading ...");
                    progressDialog.show();

                    SignUpInterface signUpInterface = getRetrofit().create(SignUpInterface.class);
                    Call<SignUpOutput> call = signUpInterface.getResult(username.getText().toString(), password.getText().toString(), 0, mobileNumber.getText().toString(), email.getText().toString(), name.getText().toString(), aadhaarNumber.getText().toString(), districtName);
                    call.enqueue(new Callback<SignUpOutput>() {
                        @Override
                        public void onResponse(Call<SignUpOutput> call, Response<SignUpOutput> response) {
                            progressDialog.dismiss();

                            if (response.body().getStatus().equals("ok")) {
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(SignUpActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                                } else {
                                    builder = new AlertDialog.Builder(SignUpActivity.this);
                                }
                                builder.setTitle("Verification")
                                        .setMessage("OTP will be send to your mobile number +91" + mobileNumber.getText().toString() + ". Please verify the OTP.")
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(SignUpActivity.this, OTPActivity.class);
                                                intent.putExtra(USER_ID, username.getText().toString());
                                                startActivity(intent);
                                            }
                                        })
                                        .show();


                            } else {
                                Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpOutput> call, Throwable t) {
                            Log.e("Error : ", t.toString());
                        }
                    });
                } else {
                    Toast.makeText(SignUpActivity.this, "Retype password not matches with password", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(SignUpActivity.this, "Make Sure that you fill all the fields correctly", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        districtName = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
