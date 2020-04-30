package com.wangyz.proxy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectHelper.inject(this);
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void click(Button view) {
        Toast.makeText(getApplicationContext(), view.getText(), Toast.LENGTH_SHORT).show();
    }

    @OnLongClick({R.id.btn1, R.id.btn2})
    public boolean longClick(Button view) {
        Toast.makeText(getApplicationContext(), view.getText() + "-LongClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
