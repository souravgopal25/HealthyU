package com.example.healthyu.networking;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {
    private static final String BASE_URL = "https://newsapi.org";
    private static Retrofit retrofit;
    private static Retrofit retrofit1;

    public static Retrofit getRetrofitInstanceNews() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }

    public static Retrofit getRetrofitInstanceStats() {
        if (retrofit1 == null) {
            retrofit1=new Retrofit.Builder().
                    baseUrl("corona.lmao.ninja").
                    addConverterFactory(GsonConverterFactory.create()).
                    build();

        }
        return retrofit1;
    }
}
