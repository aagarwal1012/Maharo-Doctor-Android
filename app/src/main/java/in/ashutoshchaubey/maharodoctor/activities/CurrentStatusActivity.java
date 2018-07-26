package in.ashutoshchaubey.maharodoctor.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData.GetSingleAppointmentDataInterface;
import in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData.GetSingleAppointmentDataOutput;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.APPOINMENT_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.EUID;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class CurrentStatusActivity extends AppCompatActivity implements View.OnClickListener {

    TextView status, doctorName, appointmentId, time, remarks;
    Button prescriptionButton;

    String app_id, euid;

    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_status);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        status = (TextView) findViewById(R.id.status_text_view_current_status);
        doctorName = (TextView) findViewById(R.id.doctor_name_current_status);
        appointmentId = (TextView) findViewById(R.id.appointment_id_current_status);
        time = (TextView) findViewById(R.id.appointment_time_current_status);
        remarks = (TextView) findViewById(R.id.remarks_text_view_current_status);

        prescriptionButton = (Button) findViewById(R.id.prescriptions_button_);
        prescriptionButton.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        euid = sharedPreferences.getString(EUID, "euid");
        try {
            app_id = getIntent().getExtras().getString(APPOINMENT_ID).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (app_id != null) {

            progressDialog = new ProgressDialog(CurrentStatusActivity.this);
            progressDialog.setTitle("Getting Data");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            GetSingleAppointmentDataInterface getSingleAppointmentDataInterface = getRetrofit().create(GetSingleAppointmentDataInterface.class);
            Call<GetSingleAppointmentDataOutput> call = getSingleAppointmentDataInterface.getResult(euid, app_id);
            call.enqueue(new Callback<GetSingleAppointmentDataOutput>() {
                @Override
                public void onResponse(Call<GetSingleAppointmentDataOutput> call, Response<GetSingleAppointmentDataOutput> response) {
                    Log.d("Get APP Data --> ", "Status : " + response.body().getStatus());

                    if (response.body().getStatus().equals("ok")) {
                        GetSingleAppointmentDataOutput getSingleAppointmentDataOutput = response.body();

                        if (getSingleAppointmentDataOutput.getData().getVerified() == 1 && getSingleAppointmentDataOutput.getData().getCompleted() == 0) {
                            status.setBackgroundColor(Color.parseColor("#76FF03"));
                            status.setText("Verified");
                        } else if (getSingleAppointmentDataOutput.getData().getVerified() == 0) {
                            status.setText("Not Verified");
                            status.setBackgroundColor(Color.parseColor("#EF5350"));
                        } else if (getSingleAppointmentDataOutput.getData().getCompleted() == 1) {
                            status.setBackgroundColor(Color.parseColor("#26C6DA"));
                            status.setText("Completed");
                        }

                        doctorName.setText("Dr. " + getSingleAppointmentDataOutput.getData().getName());

                        appointmentId.setText(app_id);

                        if (getSingleAppointmentDataOutput.getData().getTime() != null) {
                            time.setText(getSingleAppointmentDataOutput.getData().getTime());
                        }
                        remarks.setText(getSingleAppointmentDataOutput.getData().getInfo().getRemarks());

                        if (getSingleAppointmentDataOutput.getData().getInfo().getMedicines() == null) {
                            prescriptionButton.setVisibility(View.GONE);
                        }

                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<GetSingleAppointmentDataOutput> call, Throwable t) {
                    Log.e("Error : ", t.toString());
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.prescriptions_button_) {
            Intent intent = new Intent(CurrentStatusActivity.this, PrescriptionsActivity.class);
            intent.putExtra(APPOINMENT_ID, app_id + "");
            startActivity(intent);
        }
    }
}
