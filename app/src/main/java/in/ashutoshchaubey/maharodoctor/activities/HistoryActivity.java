package in.ashutoshchaubey.maharodoctor.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.adapters.AppointmentAdapter;
import in.ashutoshchaubey.maharodoctor.adapters.MedAdapter;
import in.ashutoshchaubey.maharodoctor.models.AppointmentItem;

public class HistoryActivity extends AppCompatActivity implements MedAdapter.ItemClickListener {

    ArrayList<AppointmentItem> data;
    AppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        RecyclerView appRecView = (RecyclerView) findViewById(R.id.appointment_list);
        data = new ArrayList<>();
        data.add(new AppointmentItem(12,0,23, 7, 2018, 23123, "Dr. Shalini Kapoor",
                "Arora Meds", "Bikaner", "Approved"));
        data.add(new AppointmentItem(12,0,23, 7, 2018, 23123, "Dr. Shalini Kapoor",
                "Arora Meds", "Bikaner", "Pending"));
        data.add(new AppointmentItem(12,0,23, 7, 2018, 23123, "Dr. Shalini Kapoor",
                "Arora Meds", "Bikaner", "Not Approved"));
        data.add(new AppointmentItem(12,0,23, 7, 2018, 23123, "Dr. Shalini Kapoor",
                "Arora Meds", "Bikaner", "Approved"));
        data.add(new AppointmentItem(12,0,23, 7, 2018, 23123, "Dr. Shalini Kapoor",
                "Arora Meds", "Bikaner", "Completed"));
        adapter = new AppointmentAdapter(this, data);
        appRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setClickListener(this);
        appRecView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO
    }
}
