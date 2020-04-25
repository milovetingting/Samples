package com.wangyz.mvvm.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.wangyz.mvvm.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArchitectureFragment extends Fragment {


    public ArchitectureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_architecture, container, false);
    }

}
