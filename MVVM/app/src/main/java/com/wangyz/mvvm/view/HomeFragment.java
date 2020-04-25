package com.wangyz.mvvm.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wangyz.mvvm.Config;
import com.wangyz.mvvm.R;
import com.wangyz.mvvm.adapter.HomeAdapter;
import com.wangyz.mvvm.bean.Article;
import com.wangyz.mvvm.view.custom.SpaceItemDecoration;
import com.wangyz.mvvm.viewmodel.BottomViewModel;
import com.wangyz.mvvm.viewmodel.HomeViewModel;
import com.wangyz.mvvm.viewmodel.ViewModelBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
        Log.i(Config.TAG, "HomeFragment");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!ViewModelBus.getInstance().exist(HomeViewModel.class)) {
            final HomeViewModel viewModel = ViewModelBus.getInstance().provide(this, HomeViewModel.class);
            viewModel.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
            viewModel.view = viewModel.binding.getRoot();
            viewModel.adapter = new HomeAdapter();
            viewModel.getListLiveData().observe(getViewLifecycleOwner(), new Observer<PagedList<Article>>() {
                @Override
                public void onChanged(PagedList<Article> articles) {
                    viewModel.adapter.submitList(articles);
                }
            });
            viewModel.binding.homeRv.setAdapter(viewModel.adapter);
            viewModel.binding.homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
            viewModel.binding.homeRv.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.item_margin)));
            return viewModel.view;
        }
        ViewModelBus.getInstance().get(BottomViewModel.class).view.setVisibility(View.VISIBLE);
        return ViewModelBus.getInstance().get(HomeViewModel.class).view;
    }

}
