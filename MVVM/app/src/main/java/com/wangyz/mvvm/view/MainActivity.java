package com.wangyz.mvvm.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wangyz.mvvm.R;
import com.wangyz.mvvm.databinding.ActivityMainBinding;
import com.wangyz.mvvm.viewmodel.MainViewModel;
import com.wangyz.mvvm.viewmodel.ViewModelBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = ViewModelBus.getInstance().provide(this, MainViewModel.class);
        viewModel.binding = binding;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewModelBus.getInstance().release();
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        DrawerLayout drawer = ViewModelBus.getInstance().get(MainViewModel.class).binding.mainDrawer;
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawers();
            return true;
        }
        NavController controller = Navigation.findNavController(this, R.id.content_fragment_host);
        if (controller.navigateUp()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
