package in.ashutoshchaubey.maharodoctor.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ashutoshchaubey.maharodoctor.R;
import in.ashutoshchaubey.maharodoctor.models.MedicineItem;

public class MedAdapter extends RecyclerView.Adapter<MedAdapter.medViewHolder>{

    private ArrayList<MedicineItem> data;
    private ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context c;

    public MedAdapter(Context context, ArrayList<MedicineItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.c = context;
    }

    @Override
    public medViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.medicine_item,parent,false);
        return new medViewHolder(view);
    }

    @Override
    public void onBindViewHolder(medViewHolder holder, int position) {
        holder.medName.setText(data.get(position).getMedicineName());
        holder.quantity.setText(data.get(position).getQuantity());
        holder.days.setText(data.get(position).getDays());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class medViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView medName, quantity, days;

        public medViewHolder(View itemView) {
            super(itemView);
            Typeface ab = Typeface.createFromAsset(c.getAssets(), "fonts/AlexBrush.ttf");

            medName = (TextView) itemView.findViewById(R.id.medicine_name);
            quantity = (TextView) itemView.findViewById(R.id.quantity_medicine_dosage);
            days = (TextView) itemView.findViewById(R.id.days_medicine_item);
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
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public ArrayList<MedicineItem> getData() {
        return data;
    }

    public void setData(ArrayList<MedicineItem> mData) {
        this.data = mData;
    }

}
