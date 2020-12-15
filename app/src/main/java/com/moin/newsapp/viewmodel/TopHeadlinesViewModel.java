package com.moin.newsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.moin.newsapp.viewmodel.datasource.TopHeadlinesDataSource;
import com.moin.newsapp.viewmodel.datasource.TopHeadlinesDataSourceFactory;
import com.moin.newsapp.model.Response;

public class TopHeadlinesViewModel extends ViewModel {
    private LiveData<PagedList<Response.Article>> pagedListLiveData;
    private LiveData<PageKeyedDataSource<Integer, Response.Article>> dataSourceLiveData;

    public TopHeadlinesViewModel(String category) {

        TopHeadlinesDataSourceFactory dataSourceFactory = new TopHeadlinesDataSourceFactory(category);
        dataSourceLiveData = dataSourceFactory.getDataSourceLiveData();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(TopHeadlinesDataSource.PAGE_SIZE)
                .build();

        pagedListLiveData
                = (new LivePagedListBuilder<Integer, Response.Article>(dataSourceFactory, pagedListConfig))
                .build();
    }

    public LiveData<PagedList<Response.Article>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
