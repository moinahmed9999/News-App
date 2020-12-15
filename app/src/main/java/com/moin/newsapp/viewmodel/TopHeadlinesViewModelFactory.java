package com.moin.newsapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TopHeadlinesViewModelFactory implements ViewModelProvider.Factory {
    private final String category;

    public TopHeadlinesViewModelFactory(String category) {
        this.category = category;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TopHeadlinesViewModel(category);
    }
}
