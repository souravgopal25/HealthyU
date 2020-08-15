package com.example.healthyu.networking;

import com.example.healthyu.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/v2/top-headlines?country=id&category=health&apiKey=40e6af5951764edc9f436ad66871577d")
    Call<News> getList();

}
