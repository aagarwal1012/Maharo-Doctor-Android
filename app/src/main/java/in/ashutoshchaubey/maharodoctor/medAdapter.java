package in.ashutoshchaubey.maharodoctor;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashutoshchaubey on 22/07/18.
 */

public class medAdapter extends RecyclerView.Adapter<medAdapter.medViewHolder>{

    private ArrayList<medicine> data;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context c;

    public medAdapter(Context context, ArrayList<medicine> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.c = context;
        Log.e("adshgashdjbjhasdb","CNSTR");
    }

    @Override
    public medViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.medicine_item,parent,false);
        return new medViewHolder(view);
    }

    @Override
    public void onBindViewHolder(medViewHolder holder, int position) {
        holder.medName.setText(data.get(position).getMedicineName());
        int[] dosage = data.get(position).getDosage();
        String str = dosage[0] + "-" + dosage[1] + "-" + dosage[2] + "-" + dosage[3];
        holder.medDosage.setText(str);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class medViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView medName;
        TextView medDosage;

        public medViewHolder(View itemView) {
            super(itemView);
            Typeface ab = Typeface.createFromAsset(c.getAssets(), "fonts/AlexBrush.ttf");

            medName = (TextView) itemView.findViewById(R.id.med_name);
            medDosage = (TextView) itemView.findViewById(R.id.med_dosage);
            medName.setTypeface(ab);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener!=null){
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public ArrayList<medicine> getData() {
        return data;
    }

    public void setData(ArrayList<medicine> mData) {
        this.data = mData;
    }

}
