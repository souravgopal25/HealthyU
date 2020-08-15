package com.example.healthyu.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.healthyu.model.News;
import com.example.healthyu.repo.NewsRepo;

public class NewsActivityViewModel extends AndroidViewModel {
    private String TAG= NewsActivityViewModel.class.getSimpleName();
    private Context context;
    private NewsRepo newsRepo;
    private LiveData<News> mData;
    public NewsActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        newsRepo=new NewsRepo();
        mData=newsRepo.getData(context);
    }
    public LiveData<News> getData(){
        this.mData=newsRepo.getData(context);
        return mData;
    }
}
