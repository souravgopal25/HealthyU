package com.example.healthyu.networking;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    private static final String BASE_URL="https://newsapi.org";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitInstanceNews(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }
    public static Retrofit getRetrofitInstanceStats(){
          retrofit= new Retrofit.Builder().
                    baseUrl("https://corona.lmao.ninja").
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
       return retrofit;

    }
}
