package com.moin.newsapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moin.newsapp.ui.adapter.HeadlinesPagedListAdapter;
import com.moin.newsapp.R;
import com.moin.newsapp.viewmodel.TopHeadlinesViewModel;
import com.moin.newsapp.viewmodel.TopHeadlinesViewModelFactory;

import java.util.Objects;

public class TopHeadlinesFragment extends Fragment {
    private final String category;
    private View view;
    private RecyclerView recyclerView;
    private HeadlinesPagedListAdapter adapter;
    private TopHeadlinesViewModel viewModel;

    public TopHeadlinesFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_healines, container, false);

        initUI();
        setUpAdapter();
        setOnClickListener();

        return view;
    }

    private void initUI() {
        recyclerView = view.findViewById(R.id.headlinesRecyclerView);

        viewModel = new ViewModelProvider(this,
                new TopHeadlinesViewModelFactory(category)).get(TopHeadlinesViewModel.class);
    }

    private void setUpAdapter() {
        adapter = new HeadlinesPagedListAdapter(getContext());

        recyclerView.setAdapter(adapter);

        viewModel.getPagedListLiveData().observe(getViewLifecycleOwner(), articles -> {
            adapter.submitList(articles);
        });
    }

    private void setOnClickListener() {
        adapter.setOnHeadlineClickListener(position -> {
            String url = Objects.requireNonNull(viewModel.getPagedListLiveData().getValue()).get(position).getUrl();

            NavDirections navDirections =
                    HomeFragmentDirections.actionHomeFragmentToNewsFragment(url);
            Navigation.findNavController(getView()).navigate(navDirections);
        });
    }
}