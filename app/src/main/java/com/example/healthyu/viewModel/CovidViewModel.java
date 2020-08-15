package com.example.healthyu.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.healthyu.model.Covid;
import com.example.healthyu.model.News;
import com.example.healthyu.repo.CovidRepo;
import com.example.healthyu.repo.NewsRepo;

public class CovidViewModel extends AndroidViewModel {
    private String TAG= CovidViewModel.class.getSimpleName();
    private Context context;
    private CovidRepo covidRepo;
    private LiveData<Covid> mData;
    public CovidViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        covidRepo=new CovidRepo();
        mData=covidRepo.getData(context);
    }

    public LiveData<Covid> getmData() {
        this.mData=covidRepo.getData(context);
        return mData;
    }
}
