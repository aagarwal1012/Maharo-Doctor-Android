package in.ashutoshchaubey.maharodoctor.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.adapters.MedAdapter;
import in.ashutoshchaubey.maharodoctor.models.MedicineItem;
import in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData.GetSingleAppointmentDataInterface;
import in.ashutoshchaubey.maharodoctor.models.getSingleAppointmentData.GetSingleAppointmentDataOutput;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.ashutoshchaubey.maharodoctor.Constants.APPOINMENT_ID;
import static in.ashutoshchaubey.maharodoctor.Constants.EUID;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.getRetrofit;

public class PrescriptionsActivity extends AppCompatActivity implements MedAdapter.ItemClickListener {

    TextView remarks;

    MedAdapter adapter;
    ArrayList<MedicineItem> data = new ArrayList<>();

    String app_id, euid;

    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;

    RecyclerView medsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        remarks = (TextView) findViewById(R.id.remarks_prescription);

//        data.add(new MedicineItem("Levo Cetrizine",new int[]{1,0,1,0},"lorem ipsum dolor sit amet"));
//        data.add(new MedicineItem("Cold syrup",new int[]{1,0,1,1},"lorem ipsum dolor sit amet"));
        medsRecyclerView = (RecyclerView) findViewById(R.id.prescriptions);
        medsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        euid = sharedPreferences.getString(EUID, "euid");
        try {
            app_id = getIntent().getExtras().getString(APPOINMENT_ID).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (app_id != null) {

            progressDialog = new ProgressDialog(PrescriptionsActivity.this);
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

                        remarks.setText(getSingleAppointmentDataOutput.getData().getInfo().getRemarks());

                        if (getSingleAppointmentDataOutput.getData().getInfo().getMedicines() != null) {
                            int length = getSingleAppointmentDataOutput.getData().getInfo().getMedicines().length;
                            for (int i = 0; i < length; i++) {
                                data.add(
                                        new MedicineItem(
                                                getSingleAppointmentDataOutput.getData().getInfo().getMedicines()[i].getName(),
                                                getSingleAppointmentDataOutput.getData().getInfo().getMedicines()[i].getDays() + " Days",
                                                getSingleAppointmentDataOutput.getData().getInfo().getMedicines()[i].getQuantity() + " Quantity"
                                        )
                                );

                                adapter = new MedAdapter(PrescriptionsActivity.this, data);
                                adapter.setClickListener(PrescriptionsActivity.this);
                                medsRecyclerView.setAdapter(adapter);
                            }
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
    public void onItemClick(View view, int position) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(data.get(position).getMedicineName())
                .setMessage("Medicine Description")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .show();
    }
}
