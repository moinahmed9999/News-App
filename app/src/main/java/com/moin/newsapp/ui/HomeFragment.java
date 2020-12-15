package com.moin.newsapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.moin.newsapp.Category;
import com.moin.newsapp.ui.adapter.CategoryViewPagerAdapter;
import com.moin.newsapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private View view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private List<Category> categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        initUI();
        setCategories();
        attachViewPagerAndTabLayout();

        return view;
    }

    private void initUI() {
        Toolbar toolbar = view.findViewById(R.id.toolbar_home);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
    }

    private void setCategories() {
        categories = new ArrayList<>();

        categories.add(Category.business);
        categories.add(Category.entertainment);
        categories.add(Category.health);
        categories.add(Category.science);
        categories.add(Category.sports);
        categories.add(Category.technology);
    }

    private void attachViewPagerAndTabLayout() {
        CategoryViewPagerAdapter adapter =
                new CategoryViewPagerAdapter(getChildFragmentManager(), getLifecycle(), categories);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, ((tab, position) -> {
            tab.setText(categories.get(position).getTitle());
        })).attach();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        inflater.inflate(R.menu.menu_search, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Log.d(TAG, "onOptionsItemSelected: ");
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_searchFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}