package com.wangyz.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.wangyz.jetpack.R;

public class MainFragment extends Fragment {

    private Button loginBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        loginBtn = view.findViewById(R.id.fragment_main_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", "zs");
                Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_loginFragment, bundle);
            }
        });
        return view;
    }
}
