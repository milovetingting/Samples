package com.wangyz.mvvm.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.wangyz.mvvm.R;
import com.wangyz.mvvm.databinding.FragmentBottomBinding;
import com.wangyz.mvvm.viewmodel.BottomViewModel;
import com.wangyz.mvvm.viewmodel.ViewModelBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    private FragmentBottomBinding binding;


    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!ViewModelBus.getInstance().exist(BottomViewModel.class)) {
            BottomViewModel viewModel = ViewModelBus.getInstance().provide(this, BottomViewModel.class);
            viewModel.view = inflater.inflate(R.layout.fragment_bottom, container, false);
            NavController navController = Navigation.findNavController(getActivity(), R.id.content_fragment_host);
            binding = DataBindingUtil.bind(viewModel.view);
            NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
            return viewModel.view;
        }
        return ViewModelBus.getInstance().get(BottomViewModel.class).view;
    }

}
