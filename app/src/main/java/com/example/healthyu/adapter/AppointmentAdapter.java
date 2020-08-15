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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentAdapyerViewHolder> {
    List<AppointmentRequest> mlist;
    Context context;
    final private ListItemClickListener mlistItemClickListener;

    public AppointmentAdapter(Context context, List<AppointmentRequest> mlist, ListItemClickListener mlistItemClickListener) {
        this.mlist = mlist;
        this.context = context;
        this.mlistItemClickListener = mlistItemClickListener;
    }

    public void setMlist(List<AppointmentRequest> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AppointmentAdapyerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new AppointmentAdapyerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapyerViewHolder holder, int position) {
        AppointmentRequest appointmentRequest = mlist.get(position);
        holder.name.setText(appointmentRequest.getName());
        holder.description.setText(appointmentRequest.getDescription());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedIndex, int buttonNumber);

    }

    public class AppointmentAdapyerViewHolder extends RecyclerView.ViewHolder implements Button.OnClickListener {
        TextView name, description;
        Button accept, reject;

        public AppointmentAdapyerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.detail);
            accept = itemView.findViewById(R.id.accept);
            reject = itemView.findViewById(R.id.reject);
            accept.setOnClickListener(this);
            reject.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int clicked = getAdapterPosition();
            if (view.getId() == R.id.accept) {
                mlistItemClickListener.onListItemClick(clicked, 1);

            } else if (view.getId() == R.id.reject) {
                mlistItemClickListener.onListItemClick(clicked, 0);

            }


        }
    }


}
