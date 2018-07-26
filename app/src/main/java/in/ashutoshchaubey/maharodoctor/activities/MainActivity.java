package in.ashutoshchaubey.maharodoctor.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.emergency.PanicInterface;
import in.ashutoshchaubey.maharodoctor.models.emergency.PanicOutput;
import in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output.GetAppointmentDataInterface;
import in.ashutoshchaubey.maharodoctor.models.getAppointmentData.Output.GetAppointmentDataOutput;
import in.ashutoshchaubey.maharodoctor.models.getName.GetNameInterface;
import in.ashutoshchaubey.maharodoctor.models.getName.GetNameOutput;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.APPOINMENT_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.EUID;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.USER_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    CircleImageView profilePic;

    Button emergency_button;

    TextView todayDate, name, verified, notVerified, current, prescription, history;

    ProgressDialog progressDialog;

    String uid, euid;

    GetAppointmentDataOutput getAppointmentDataOutput;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emergency_button = (Button) findViewById(R.id.emergency_button);
        emergency_button.setOnClickListener(this);

        name = (TextView) findViewById(R.id.first_name_main_activity);
        name.addTextChangedListener(this);
        todayDate = (TextView) findViewById(R.id.todays_date_main_activity);
        todayDate.addTextChangedListener(this);
        verified = (TextView) findViewById(R.id._number_of_verified_main_activity);
        verified.addTextChangedListener(this);
        notVerified = (TextView) findViewById(R.id.number_of_not_verified_main_activity);
        notVerified.addTextChangedListener(this);
        current = (TextView) findViewById(R.id.current_status_main_activity);
        current.addTextChangedListener(this);
        prescription = (TextView) findViewById(R.id.current_prescriptions_main_activity);
        prescription.addTextChangedListener(this);
        history = (TextView) findViewById(R.id.history_main_activity);
        history.addTextChangedListener(this);

        profilePic = (CircleImageView) findViewById(R.id.profile_image_main_activity);
        profilePic.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        uid = sharedPreferences.getString(USER_ID, "uid");
        euid = sharedPreferences.getString(EUID, "euid");

        findViewById(R.id.fab_make_appointment_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MakeAppointmentActivity.class));
            }
        });

        findViewById(R.id.prescription_linear_layout_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrescriptionsActivity.class);
                intent.putExtra(APPOINMENT_ID, getAppointmentDataOutput.getData()[getAppointmentDataOutput.getData().length - 1].getIdsitings() + "");
                startActivity(intent);
            }
        });

        findViewById(R.id.current_status_linear_layout_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentStatusActivity.class);
                intent.putExtra(APPOINMENT_ID, getAppointmentDataOutput.getData()[getAppointmentDataOutput.getData().length - 1].getIdsitings() + "");
                startActivity(intent);
            }
        });

        findViewById(R.id.history_linear_layout_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });

        findViewById(R.id.pricing_linear_layout_main_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Coming Soon", Toast.LENGTH_SHORT);
            }
        });

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Getting Profile");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetNameInterface getNameInterface = getRetrofit().create(GetNameInterface.class);
        Call<GetNameOutput> call = getNameInterface.getResult(uid);
        call.enqueue(new Callback<GetNameOutput>() {
            @Override
            public void onResponse(Call<GetNameOutput> call, Response<GetNameOutput> response) {
                Log.d("Response Get Name --> ", "Status : " + response.body().getStatus());

                if (response.body().getStatus().equals("ok")) {
                    GetNameOutput getNameOutput = response.body();
                    name.setText(getNameOutput.getName());
                }
            }

            @Override
            public void onFailure(Call<GetNameOutput> call, Throwable t) {
                Log.e("Error : ", t.toString());
            }
        });

        final GetAppointmentDataInterface getAppointmentDataInterface = getRetrofit().create(GetAppointmentDataInterface.class);
        Call<GetAppointmentDataOutput> call1 = getAppointmentDataInterface.getResult(euid);
        call1.enqueue(new Callback<GetAppointmentDataOutput>() {
            @Override
            public void onResponse(Call<GetAppointmentDataOutput> call, Response<GetAppointmentDataOutput> response) {
                Log.d("Response Get Name --> ", "Status : " + response.body().getStatus());

                if (response.body().getStatus().equals("ok")) {
                    getAppointmentDataOutput = response.body();
                    int ver = 0, not_ver = 0;
                    int length = getAppointmentDataOutput.getData().length;
                    for (int i = 0; i < length; i++) {
                        if (getAppointmentDataOutput.getData()[i].getVerified() == 1) {
                            ver++;
                        } else {
                            not_ver++;
                        }
                    }
                    verified.setText(ver + "");
                    notVerified.setText(not_ver + "");
                    history.setText(length + " Appointments");
                    if (getAppointmentDataOutput.getData()[length - 1].getInfo() != null) {
                        prescription.setText(getAppointmentDataOutput.getData()[length - 1].getInfo().getMedicines().length + " Total");
                    } else {
                        prescription.setText("No Information Available");
                    }
                    if (getAppointmentDataOutput.getData()[length - 1].getCompleted() == 1) {
                        current.setText("Completed");
                    } else {
                        current.setText("Not Completed");
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAppointmentDataOutput> call, Throwable t) {
                Log.e("Error : ", t.toString());
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (name.getText() != null && verified.getText() != null && notVerified.getText() != null && current.getText() != null && prescription.getText() != null && history.getText() != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.profile_image_main_activity) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        } else if (view.getId() == R.id.emergency_button) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_LIGHT);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle("Request Id")
                    .setMessage("Are you sure its an Emergency!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            progressDialog.setTitle("Getting a Ambulance");
                            progressDialog.setMessage("Loading...");
                            progressDialog.show();

                            PanicInterface panicInterface = getRetrofit().create(PanicInterface.class);
                            Call<PanicOutput> call2 = panicInterface.getResult(euid, uid);
                            call2.enqueue(new Callback<PanicOutput>() {
                                @Override
                                public void onResponse(Call<PanicOutput> call, Response<PanicOutput> response) {
                                    if (response.body().getStatus().equals("ok")){
                                        progressDialog.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<PanicOutput> call, Throwable t) {
                                    Log.e("Error : ", t.toString());
                                }
                            });
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .show();
        }
    }
}
