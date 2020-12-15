package com.moin.newsapp.network;

import com.moin.newsapp.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/v2/top-headlines")
    Call<Response> getHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/everything")
    Call<Response> getEverything(
            @Query("q") String query,
            @Query("language") String language,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("apiKey") String apiKey
    );
}
