package com.moin.newsapp.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.moin.newsapp.Category;
import com.moin.newsapp.ui.TopHeadlinesFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList;

    public CategoryViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Category> categories) {
        super(fragmentManager, lifecycle);
        this.fragmentList = new ArrayList<>();

        for (Category category : categories) {
            fragmentList.add(new TopHeadlinesFragment(category.name()));
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
