package com.example.healthyu.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthyu.R;
import com.example.healthyu.adapter.ListItemClickListener;
import com.example.healthyu.adapter.NewsAdapter;
import com.example.healthyu.model.News;
import com.example.healthyu.networking.ApiInterface;
import com.example.healthyu.networking.RetrofitRequest;
import com.example.healthyu.viewModel.NewsActivityViewModel;

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
    News mNews;
    private static String TAG=NewsActivity.class.getSimpleName();
    NewsActivityViewModel newsActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setHasFixedSize(true);
        newsAdapter=new NewsAdapter(this,this);
        recycler.setAdapter(newsAdapter);
        newsActivityViewModel= ViewModelProviders.of(this).get(NewsActivityViewModel.class);
        newsActivityViewModel.getData().observe(this, new Observer<News>() {
            @Override
            public void onChanged(News news) {
                mNews=news;
                mlist=mNews.getArticles();
                newsAdapter.setMlist(mlist);

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