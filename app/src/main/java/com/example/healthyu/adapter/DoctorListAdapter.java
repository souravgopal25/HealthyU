package com.example.healthyu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorListAdapterViewHolder>{
    private List<Doctor> mlist;
    private Context context;
    private ListItemClickListener mlistItemClickListener;

    public DoctorListAdapter(Context context, ListItemClickListener mlistItemClickListener) {
        this.context = context;
        this.mlist=new ArrayList<>();
        this.mlistItemClickListener = mlistItemClickListener;
    }

    public void setMlist(List<Doctor> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoctorListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_drlist_layout, parent, false);
        return new DoctorListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListAdapterViewHolder holder, int position) {
        Doctor doctor=mlist.get(position);
        holder.mName.setText(doctor.getName());
        holder.mSpecialization.setText(doctor.getDept());
        holder.mHospital.setText("Jakarata Hospital");

    }

    @Override
    public int getItemCount() {
        return mlist==null?0:mlist.size();
    }

    public class DoctorListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mName,mHospital,mSpecialization;

        public DoctorListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mName=itemView.findViewById(R.id.name);
            mHospital=itemView.findViewById(R.id.mail);
            mSpecialization=itemView.findViewById(R.id.specialization);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clicked=getAdapterPosition();
            mlistItemClickListener.onItemClick(clicked);


        }
    }
}
