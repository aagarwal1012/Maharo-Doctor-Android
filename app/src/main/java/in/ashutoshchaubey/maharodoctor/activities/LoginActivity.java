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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.login.LoginInterface;
import in.ashutoshchaubey.maharodoctor.models.login.Output.OutputLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.EUID;
import static in.ashutoshchaubey.maharodoctor.Constants.IS_LOGGED_IN;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.USER_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userName, password;
    TextView signUp;
    Button signInButton;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout loginParent = (RelativeLayout) findViewById(R.id.log_in_parent);
        loginParent.getBackground().setAlpha(128);

        signUp = (TextView) findViewById(R.id.sign_up_text_view);

        userName = (EditText) findViewById(R.id.username_edit_text);
        password = (EditText) findViewById(R.id.password_edit_text);

        signInButton = (Button) findViewById(R.id.button_sign_in);

        progressDialog = new ProgressDialog(LoginActivity.this);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        signInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_sign_in) {
            if (userName.getText() != null && password.getText() != null) {

                progressDialog.setTitle("Logging In");
                progressDialog.setMessage("Loading ...");
                progressDialog.show();

                LoginInterface loginInterface = getRetrofit().create(LoginInterface.class);

                Call<OutputLogin> call = loginInterface.getResult(userName.getText().toString(), password.getText().toString());
                call.enqueue(new Callback<OutputLogin>() {
                    @Override
                    public void onResponse(Call<OutputLogin> call, Response<OutputLogin> response) {

                        Log.d("Response Login " , "Status : " + response.body().getStatus() + ", Message : " + response.body().getMessage() + ", EUid : " + response.body().getEuid());
                        progressDialog.dismiss();

                        if (response.body().getStatus().equals("ok")) {
                            OutputLogin outputLogin = response.body();

                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();

                            sharedPreferences.edit().putBoolean(IS_LOGGED_IN, true).commit();
                            sharedPreferences.edit().putString(USER_ID, userName.getText().toString()).commit();
                            sharedPreferences.edit().putString(EUID, outputLogin.getEuid()).commit();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "Username or password are invalid", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OutputLogin> call, Throwable t) {
                        Log.e("Error : ", t.toString() );
                    }
                });

            } else {
                Toast.makeText(LoginActivity.this, "Make Sure that you fill all the fields correctly", Toast.LENGTH_LONG).show();
            }
        }
    }
}
