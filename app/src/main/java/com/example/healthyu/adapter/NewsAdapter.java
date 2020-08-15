package com.example.healthyu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthyu.R;
import com.example.healthyu.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {
    private Context context;
    private List<News.ArticlesBean> mlist;
    private ListItemClickListener mitemClickListener;

    public NewsAdapter(Context context, ListItemClickListener mitemClickListener) {
        this.context = context;
        this.mlist = new ArrayList<>();
        this.mitemClickListener = mitemClickListener;
    }

    public void setMlist(List<News.ArticlesBean> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item, parent, false);
        return new NewsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterViewHolder holder, int position) {
        News.ArticlesBean obj=mlist.get(position);
        Glide.with(context).load(obj.getUrlToImage()).into(holder.imageView);
        holder.title.setText(obj.getTitle());
        holder.description.setText(obj.getDescription());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public  class NewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title,description;

        public NewsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.thumbnail);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clicked=getAdapterPosition();
            mitemClickListener.onItemClick(clicked);


        }
    }

}
