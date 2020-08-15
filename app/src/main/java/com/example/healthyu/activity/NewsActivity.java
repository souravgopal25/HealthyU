package com.example.healthyu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.adapter.ListItemClickListener;
import com.example.healthyu.adapter.NewsAdapter;
import com.example.healthyu.model.News;
import com.example.healthyu.networking.ApiInterface;
import com.example.healthyu.networking.RetrofitRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity implements ListItemClickListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    List<News.ArticlesBean> mlist;
    ApiInterface apiInterface;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);

        newsAdapter=new NewsAdapter(this,this);
        apiInterface= RetrofitRequest.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getList().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
               News news=response.body();
              if (mlist==null){
                  mlist=news.getArticles();
              }else {
                  mlist.clear();
                  mlist.addAll(news.getArticles());
              }
               newsAdapter.setMlist(mlist);
               recycler.setAdapter(newsAdapter);

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Problem in Parsing News", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public void onItemClick(int clickedindex) {

        News.ArticlesBean obj= mlist.get(clickedindex);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(obj.getUrl()));
        startActivity(intent);


    }
}