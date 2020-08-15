package com.example.healthyu.repo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthyu.model.News;
import com.example.healthyu.networking.ApiInterface;
import com.example.healthyu.networking.RetrofitRequest;
import com.example.healthyu.utils.NetworkCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {
    private final String TAG=NewsRepo.class.getSimpleName();
    private ApiInterface apiInterface;

    public NewsRepo() {
        apiInterface= RetrofitRequest.getRetrofitInstanceNews().create(ApiInterface.class);
    }
    public LiveData<News> getData(Context context){
        final MutableLiveData<News> data=new MutableLiveData<>();
        if (NetworkCheck.connected(context)){
            apiInterface.getList().enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    data.setValue(response.body());
                    Log.d(TAG,"DATA RECIEVED   " +response.body().getArticles().size());
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    data.setValue(null);
                    Log.e(TAG,t.getMessage());
                }
            });

        }
        return data;
    }

}
