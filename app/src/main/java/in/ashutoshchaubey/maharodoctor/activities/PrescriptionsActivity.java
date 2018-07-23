package in.ashutoshchaubey.maharodoctor.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.adapters.MedAdapter;
import in.ashutoshchaubey.maharodoctor.models.MedicineItem;

public class PrescriptionsActivity extends AppCompatActivity implements MedAdapter.ItemClickListener {

    MedAdapter adapter;
    ArrayList<MedicineItem> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);

        Typeface lobster = Typeface.createFromAsset(getApplication().getAssets(), "fonts/lobster.otf");
        TextView toolbarText = (TextView) findViewById(R.id.toolbar);
        toolbarText.setTypeface(lobster);

        data.add(new MedicineItem("Levo Cetrizine",new int[]{1,0,1,0},"lorem ipsum dolor sit amet"));
        data.add(new MedicineItem("Cold syrup",new int[]{1,0,1,1},"lorem ipsum dolor sit amet"));
        RecyclerView medsRecyclerView = (RecyclerView) findViewById(R.id.prescriptions);
        medsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedAdapter(this, data);
        adapter.setClickListener(this);
        medsRecyclerView.setAdapter(adapter);

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
                .setMessage(data.get(position).getMedicineDescription())
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete MMLL
                    }
                })
                .show();
    }
}
