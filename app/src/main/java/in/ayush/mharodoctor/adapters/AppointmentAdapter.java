package in.ayush.mharodoctor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.ayush.mharodoctor.R;
import in.ayush.mharodoctor.models.AppointmentItem;

/**
 * Created by ashutoshchaubey on 23/07/18.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.appointViewHolder> {

    private ArrayList<AppointmentItem> data;
    private MedAdapter.ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context c;
    private String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
    "Nov", "Dec"};

    public AppointmentAdapter(Context context, ArrayList<AppointmentItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
        this.c = context;
    }

    @Override
    public AppointmentAdapter.appointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.appointment_item,parent,false);
        return new AppointmentAdapter.appointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointmentAdapter.appointViewHolder holder, int position) {
        if (data.get(position).getMm() != null){
            holder.date.setText(data.get(position).getDd() +" "+ data.get(position).getMm() +", "+ data.get(position).getYyyy());
            holder.time.setText(data.get(position).getHour()+":"+data.get(position).getMinute()+" hrs");
        }
        else {
            holder.date.setText("None");
            holder.time.setText("None");
        }
        holder.docName.setText(data.get(position).getDocName());
        holder.status.setText(data.get(position).getStatus());
        if (data.get(position).getStatus().equals("Pending")){
            holder.status.setBackgroundColor(Color.parseColor("#FFEE58"));
        }else if(data.get(position).getStatus().equals("Verified")){
            holder.status.setBackgroundColor(Color.parseColor("#76FF03"));
        }else if (data.get(position).getStatus().equals("Not Verified")){
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
    public void setClickListener(MedAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public ArrayList<AppointmentItem> getData() {
        return data;
    }

    public void setData(ArrayList<AppointmentItem> mData) {
        this.data = mData;
    }
}
