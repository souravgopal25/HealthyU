package com.example.healthyu.repo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthyu.model.Covid;
import com.example.healthyu.model.News;
import com.example.healthyu.networking.ApiInterface;
import com.example.healthyu.networking.RetrofitRequest;
import com.example.healthyu.utils.NetworkCheck;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidRepo {
    private final String TAG=CovidRepo.class.getSimpleName();
    private ApiInterface apiInterface;

    public CovidRepo() {
        apiInterface= RetrofitRequest.getRetrofitInstanceStats().create(ApiInterface.class);
    }
    public LiveData<Covid> getData(Context context){
        final MutableLiveData<Covid> data=new MutableLiveData<>();
        if (NetworkCheck.connected(context)){
            apiInterface.getData().enqueue(new Callback<Covid>() {
                @Override
                public void onResponse(Call<Covid> call, Response<Covid> response) {
                    data.setValue(response.body());
                    Log.d(TAG,"DATA RECIEVED  " );
                }

                @Override
                public void onFailure(Call<Covid> call, Throwable t) {
                    data.setValue(null);
                    Log.e(TAG,t.getMessage());

                }
            });

        }
        return data;
    }
}
