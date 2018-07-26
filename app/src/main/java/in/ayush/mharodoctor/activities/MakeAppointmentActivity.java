package in.ayush.mharodoctor.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import in.ayush.mharodoctor.R;
import in.ayush.mharodoctor.models.getNearbyDoctors.GetNearByDoctorsInterface;
import in.ayush.mharodoctor.models.getNearbyDoctors.GetNearByDoctorsOutput;
import in.ayush.mharodoctor.models.requestAppointment.RequestAppointmentInterface;
import in.ayush.mharodoctor.models.requestAppointment.RequestAppointmentOutput;
import in.ayush.mharodoctor.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeAppointmentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner doctor;

    Button confirmAppointment;

    EditText date;
    private int mYear, mMonth, mDay;

    ProgressDialog progressDialog;

    String uid, euid;

    String[] doctorList, doctorIdList;

    ArrayAdapter<String> arrayAdapter;

    String selectedId;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        doctor = (Spinner) findViewById(R.id.doctor_name_spinnner);

        confirmAppointment = (Button) findViewById(R.id.confirm_appointment_button);
        confirmAppointment.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        uid = sharedPreferences.getString(Constants.USER_ID, "uid");
        euid = sharedPreferences.getString(Constants.EUID, "euid");

        progressDialog = new ProgressDialog(MakeAppointmentActivity.this);
        progressDialog.setTitle("Getting Doctors Near You");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final InputMethodManager imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        date = (EditText) findViewById(R.id.appointment_date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                final TextView dateValidityText = (TextView) findViewById(R.id.date_warning);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MakeAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                Calendar cal = Calendar.getInstance();
                                if (year < cal.get(Calendar.YEAR)) {
                                    dateValidityText.setVisibility(View.VISIBLE);
                                } else {
                                    if (year == cal.get(Calendar.YEAR)) {
                                        if (monthOfYear < cal.get(Calendar.MONTH)) {
                                            dateValidityText.setVisibility(View.VISIBLE);
                                        } else if (monthOfYear == cal.get(Calendar.MONTH)) {
                                            if (dayOfMonth <= cal.get(Calendar.DAY_OF_MONTH)) {
                                                dateValidityText.setVisibility(View.VISIBLE);
                                            } else {
                                                dateValidityText.setVisibility(View.GONE);
                                            }
                                        } else {
                                            dateValidityText.setVisibility(View.GONE);
                                        }
                                    } else {
                                        dateValidityText.setVisibility(View.GONE);
                                    }
                                }
                                imm.hideSoftInputFromWindow(date.getWindowToken(), 0);
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        GetNearByDoctorsInterface getNearByDoctorsInterface = Constants.getRetrofit().create(GetNearByDoctorsInterface.class);
        Call<GetNearByDoctorsOutput> call = getNearByDoctorsInterface.getResult(euid);
        call.enqueue(new Callback<GetNearByDoctorsOutput>() {
            @Override
            public void onResponse(Call<GetNearByDoctorsOutput> call, Response<GetNearByDoctorsOutput> response) {
                Log.d("Getting Doctor --> ", "Status : " + response.body().getStatus());
                if (response.body().getStatus().equals("ok")) {
                    GetNearByDoctorsOutput getNearByDoctorsOutput = response.body();

                    int length = getNearByDoctorsOutput.getList().length;

                    doctorList = new String[length];
                    doctorIdList = new String[length];

                    for (int i = 0; i < length; i++) {
                        doctorList[i] = getNearByDoctorsOutput.getList()[i].getName();
                        doctorIdList[i] = getNearByDoctorsOutput.getList()[i].getUid();
                    }

                    arrayAdapter = new ArrayAdapter<String>(MakeAppointmentActivity.this, android.R.layout.simple_spinner_dropdown_item, doctorList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    doctor.setAdapter(arrayAdapter);
                    doctor.setOnItemSelectedListener(MakeAppointmentActivity.this);
                    if (doctorIdList != null ){
                        try{
                            selectedId = doctorIdList[0];
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GetNearByDoctorsOutput> call, Throwable t) {
                Log.e("Error : ", t.toString());
            }
        });


    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_appointment_button) {
            progressDialog.setTitle("Confirming Appointment");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            RequestAppointmentInterface requestAppointmentInterface = Constants.getRetrofit().create(RequestAppointmentInterface.class);
            Call<RequestAppointmentOutput> call1 = requestAppointmentInterface.getResult(euid, selectedId, date.getText().toString(), uid);
            call1.enqueue(new Callback<RequestAppointmentOutput>() {
                @Override
                public void onResponse(Call<RequestAppointmentOutput> call, Response<RequestAppointmentOutput> response) {
                    Log.d("Confirm Appt --> ", "Status : " + response.body().getStatus());
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("ok")) {
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(MakeAppointmentActivity.this, AlertDialog.THEME_HOLO_LIGHT);
                        } else {
                            builder = new AlertDialog.Builder(MakeAppointmentActivity.this);
                        }
                        builder.setTitle("Request Id")
                                .setMessage("Please note down the request id : " + response.body().getRequest_id())
                                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MakeAppointmentActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();

                    }
                }

                @Override
                public void onFailure(Call<RequestAppointmentOutput> call, Throwable t) {
                    Log.e("Error : ", t.toString());
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedId = doctorIdList[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
