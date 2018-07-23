package in.ashutoshchaubey.maharodoctor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashutoshchaubey on 23/07/18.
 */

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.appointViewHolder> {

    private ArrayList<appointment> data;
    private medAdapter.ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context c;
    private String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
    "Nov", "Dec"};

    public appointmentAdapter(Context context, ArrayList<appointment> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.c = context;
    }

    @Override
    public appointmentAdapter.appointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.appointment_item,parent,false);
        return new appointmentAdapter.appointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(appointmentAdapter.appointViewHolder holder, int position) {
        holder.date.setText(data.get(position).getDd() +" "+ months[data.get(position).getMm()-1] +", "+ data.get(position).getYyyy());
        holder.time.setText(data.get(position).getHour()+":"+data.get(position).getMinute()+" hrs");
        holder.docName.setText(data.get(position).getDocName());
        holder.clinic.setText(data.get(position).getClinicName()+", "+data.get(position).getCityName());
        holder.status.setText(data.get(position).getStatus());
        if (data.get(position).getStatus().equals("Pending")){
            holder.status.setBackgroundColor(Color.parseColor("#FFEE58"));
        }else if(data.get(position).getStatus().equals("Approved")){
            holder.status.setBackgroundColor(Color.parseColor("#76FF03"));
        }else if (data.get(position).getStatus().equals("Not Approved")){
            holder.status.setBackgroundColor(Color.parseColor("#EF5350"));
        }else{
            holder.status.setBackgroundColor(Color.parseColor("#26C6DA"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class appointViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date;
        TextView time, status, docName, clinic;

        public appointViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.appoint_date);
            time = (TextView) itemView.findViewById(R.id.appointment_time);
            status = (TextView) itemView.findViewById(R.id.status);
            docName = (TextView) itemView.findViewById(R.id.doc_name);
            clinic = (TextView) itemView.findViewById(R.id.clin_name);

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
    void setClickListener(medAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public ArrayList<appointment> getData() {
        return data;
    }

    public void setData(ArrayList<appointment> mData) {
        this.data = mData;
    }
}
