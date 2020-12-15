package com.moin.newsapp.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.moin.newsapp.model.Response;

public class TopHeadlinesDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Response.Article>> dataSourceLiveData
            = new MutableLiveData<>();
    private final String category;

    public TopHeadlinesDataSourceFactory(String category) {
        this.category = category;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Response.Article>> getDataSourceLiveData() {
        return dataSourceLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {
        TopHeadlinesDataSource dataSource = new TopHeadlinesDataSource(category);

        dataSourceLiveData.postValue(dataSource);

        return dataSource;
    }
}
