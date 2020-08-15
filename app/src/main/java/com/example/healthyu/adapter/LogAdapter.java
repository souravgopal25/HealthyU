package com.example.healthyu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.model.AppointmentRequest;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogAdapterViewHolder> {
    List<AppointmentRequest> mlist;
    Context context;

    public LogAdapter(Context context) {
        this.mlist = new ArrayList<>();
        this.context = context;
    }
    public void setMlist(List<AppointmentRequest> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public LogAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_log, parent, false);
        return new LogAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapterViewHolder holder, int position) {
        holder.name.setText(mlist.get(position).getName());
        holder.description.setText(mlist.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class LogAdapterViewHolder extends RecyclerView.ViewHolder  {
        TextView name, description;

        public LogAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.detail);



        }
    }
}
