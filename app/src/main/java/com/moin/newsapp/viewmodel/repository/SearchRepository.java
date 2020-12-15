package com.moin.newsapp.viewmodel.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.moin.newsapp.model.Response;
import com.moin.newsapp.viewmodel.datasource.SearchDataSource;
import com.moin.newsapp.viewmodel.datasource.SearchDataSourceFactory;

public class SearchRepository {

    public LiveData<PagedList<Response.Article>> search(String query) {
        SearchDataSourceFactory dataSourceFactory = new SearchDataSourceFactory(query);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(SearchDataSource.PAGE_SIZE)
                        .build();

        return (new LivePagedListBuilder<Integer, Response.Article>(dataSourceFactory, pagedListConfig))
                .build();
    }
}
