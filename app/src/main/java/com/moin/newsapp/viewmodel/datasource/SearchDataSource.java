package com.moin.newsapp.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.moin.newsapp.BuildConfig;
import com.moin.newsapp.model.Response;
import com.moin.newsapp.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchDataSource extends PageKeyedDataSource<Integer, Response.Article> {
    public static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;

    private static final String LANGUAGE = "en";
        private static final String API_KEY = BuildConfig.API_KEY;
    private final String query;

    public SearchDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Response.Article> callback) {

        ApiClient.getInstance().getHeadlinesApi()
                .getEverything(query, LANGUAGE, PAGE_SIZE, FIRST_PAGE, API_KEY)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        if (response.body() != null) {
                            callback.onResult(response.body().getArticles(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Response.Article> callback) {

        ApiClient.getInstance().getHeadlinesApi()
                .getEverything(query, LANGUAGE, PAGE_SIZE, params.key, API_KEY)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        if (response.body() != null) {
                            callback.onResult(response.body().getArticles(), FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Response.Article> callback) {

        ApiClient.getInstance().getHeadlinesApi()
                .getEverything(query, LANGUAGE, PAGE_SIZE, params.key, API_KEY)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                        if (response.body() != null) {
                            int totalResults = Integer.parseInt(response.body().getTotalResults());

                            Integer adjacentPageKey = (params.key * PAGE_SIZE < totalResults) ? params.key + 1 : null;

                            callback.onResult(response.body().getArticles(), adjacentPageKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }
}
