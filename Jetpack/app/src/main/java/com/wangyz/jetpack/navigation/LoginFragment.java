package com.wangyz.jetpack.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.wangyz.jetpack.R;

public class LoginFragment extends Fragment {

    private Button backBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString("name", "null");
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        backBtn = view.findViewById(R.id.fragment_login_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });
        return view;
    }
}
