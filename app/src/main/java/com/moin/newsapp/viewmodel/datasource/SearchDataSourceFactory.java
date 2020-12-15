package com.moin.newsapp.viewmodel.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.moin.newsapp.model.Response;

public class SearchDataSourceFactory extends DataSource.Factory {
    private final MutableLiveData<PageKeyedDataSource<Integer, Response.Article>> dataSourceLiveData
            = new MutableLiveData<>();
    private final String query;

    public SearchDataSourceFactory(String query) {
        this.query = query;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Response.Article>> getDataSourceLiveData() {
        return dataSourceLiveData;
    }

    @NonNull
    @Override
    public DataSource create() {
        SearchDataSource dataSource = new SearchDataSource(query);

        dataSourceLiveData.postValue(dataSource);

        return dataSource;
    }
}
