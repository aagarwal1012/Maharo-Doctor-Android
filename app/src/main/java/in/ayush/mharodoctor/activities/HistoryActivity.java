package in.ayush.mharodoctor.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.ayush.mharodoctor.R;
import in.ayush.mharodoctor.adapters.AppointmentAdapter;
import in.ayush.mharodoctor.adapters.MedAdapter;
import in.ayush.mharodoctor.models.AppointmentItem;
import in.ayush.mharodoctor.models.getAppointmentData.Output.AppointmentData;
import in.ayush.mharodoctor.models.getAppointmentData.Output.GetAppointmentDataInterface;
import in.ayush.mharodoctor.models.getAppointmentData.Output.GetAppointmentDataOutput;
import in.ayush.mharodoctor.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements MedAdapter.ItemClickListener {

    ArrayList<AppointmentItem> data;
    AppointmentAdapter adapter;

    String uid, euid;

    SharedPreferences sharedPreferences;

    GetAppointmentDataOutput getAppointmentDataOutput;

    ProgressDialog progressDialog;

    RecyclerView appRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        appRecView = (RecyclerView) findViewById(R.id.appointment_list);
        data = new ArrayList<>();

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        uid = sharedPreferences.getString(Constants.USER_ID, "uid");
        euid = sharedPreferences.getString(Constants.EUID, "euid");

        progressDialog = new ProgressDialog(HistoryActivity.this);
        progressDialog.setTitle("Getting History");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetAppointmentDataInterface getAppointmentDataInterface = Constants.getRetrofit().create(GetAppointmentDataInterface.class);
        Call<GetAppointmentDataOutput> call1 = getAppointmentDataInterface.getResult(euid);
        call1.enqueue(new Callback<GetAppointmentDataOutput>() {
            @Override
            public void onResponse(Call<GetAppointmentDataOutput> call, Response<GetAppointmentDataOutput> response) {
                Log.d("Response History --> ", "Status : " + response.body().getStatus());

                if (response.body().getStatus().equals("ok")) {
                    getAppointmentDataOutput = response.body();
                    AppointmentData[] appointmentData = getAppointmentDataOutput.getData();
                    int length = appointmentData.length;
                    for (int i = length - 1; i >= 0; i--) {
                        if (appointmentData[i].getVerified() == 0) {
                            data.add(
                                    new AppointmentItem(
                                            appointmentData[i].getTime(),
                                            appointmentData[i].getIdsitings(),
                                            appointmentData[i].getName(),
                                            "Not Verified")
                            );
                        } else {
                            if (appointmentData[i].getTime() != null) {
                                data.add(
                                        new AppointmentItem(
                                                appointmentData[i].getTime(),
                                                appointmentData[i].getIdsitings(),
                                                appointmentData[i].getName(),
                                                "Verified")
                                );
                            } else {
                                data.add(
                                        new AppointmentItem(
                                                appointmentData[i].getTime(),
                                                appointmentData[i].getIdsitings(),
                                                appointmentData[i].getName(),
                                                "Completed")
                                );
                            }
                        }
                    }
                    adapter = new AppointmentAdapter(HistoryActivity.this, data);
                    appRecView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
                    adapter.setClickListener(HistoryActivity.this);
                    appRecView.setAdapter(adapter);

                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetAppointmentDataOutput> call, Throwable t) {
                Log.e("Error : ", t.toString());
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(HistoryActivity.this, CurrentStatusActivity.class);
        intent.putExtra(Constants.APPOINMENT_ID, data.get(position).getId() + "");
        startActivity(intent);
    }
}
