package com.moin.newsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.moin.newsapp.viewmodel.repository.SearchRepository;
import com.moin.newsapp.model.Response;
import com.moin.newsapp.viewmodel.repository.SearchRepository;

public class SearchViewModel extends ViewModel {
    private final SearchRepository repository = new SearchRepository();
    private final MutableLiveData<String> queryLiveData = new MutableLiveData<>();

    private final LiveData<PagedList<Response.Article>> pagedListLiveData =
            Transformations.switchMap(queryLiveData, query -> {
                return repository.search(query);
            });

    public LiveData<PagedList<Response.Article>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public void search(String query) {
        queryLiveData.postValue(query);
    }
}
