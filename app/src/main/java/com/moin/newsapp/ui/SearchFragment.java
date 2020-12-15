package com.moin.newsapp.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.moin.newsapp.ui.adapter.HeadlinesPagedListAdapter;
import com.moin.newsapp.R;
import com.moin.newsapp.viewmodel.SearchViewModel;

import java.util.Objects;

public class SearchFragment extends Fragment {
    private View view;
    private ImageView backArrow;
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private HeadlinesPagedListAdapter adapter;
    private SearchViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        initUI();
        initAdapter();
        setOnClickListener();
        observeEditText();

        return view;
    }

    private void initUI() {
        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        backArrow = view.findViewById(R.id.iv_back_arrow);
        searchEditText = view.findViewById(R.id.et_search);

        recyclerView = view.findViewById(R.id.searchRecyclerView);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    private void initAdapter() {
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
                    SearchFragmentDirections.actionSearchFragmentToNewsFragment(url);
            Navigation.findNavController(getView()).navigate(navDirections);
        });

        backArrow.setOnClickListener(v -> {
            Navigation.findNavController(getView()).popBackStack();
        });
    }

    void observeEditText() {

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String query = charSequence.toString();
                if (!query.trim().isEmpty()) {
                    viewModel.search(query);
                    adapter.submitList(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onResume() {
        view.findViewById(R.id.search_linear_layout).postDelayed(() -> {
            searchEditText.requestFocus();

            InputMethodManager inputMethodManager = (InputMethodManager) (getContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);

        }, 100);

        super.onResume();
    }
}