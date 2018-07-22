package in.ashutoshchaubey.maharodoctor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionsActivity extends AppCompatActivity implements medAdapter.ItemClickListener {

    medAdapter adapter;
    ArrayList<medicine> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);

        data.add(new medicine("Levo Cetrizine",new int[]{1,0,1,0},"lorem ipsum dolor sit amet"));
        data.add(new medicine("Cold syrup",new int[]{1,0,1,1},"lorem ipsum dolor sit amet"));
        RecyclerView medsRecyclerView = (RecyclerView) findViewById(R.id.prescriptions);
        medsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new medAdapter(this, data);
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
