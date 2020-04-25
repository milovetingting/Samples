package com.wangyz.jetpack.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/14 16:04
 * @description DataBindingActivity
 */
public class DataBindingActivity extends AppCompatActivity {

    User user;

    ActivityDatabindingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        user = new User("张三", 18);
        binding.setUser(user);
    }

    public void update(View view) {
        user.setName(user.getName() + "$");
        user.setAge(user.getAge() + 1);
        binding.setVariable(com.wangyz.jetpack.BR.user, user);
    }
}
