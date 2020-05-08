package com.wangyz.ioc.runtime;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wangyz.ioc.runtime.annotation.LayoutInject;
import com.wangyz.ioc.runtime.annotation.OnClick;
import com.wangyz.ioc.runtime.annotation.OnLongClick;
import com.wangyz.ioc.runtime.annotation.ViewInject;
import com.wangyz.ioc.runtime.util.InjectUtil;

/**
 * @author wangyz
 */
@LayoutInject(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.btn1)
    Button btn1;

    @ViewInject(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtil.inject(this);
        btn1.setText("按钮1");
        btn2.setText("按钮2");
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void click(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn1:
                Toast.makeText(getApplicationContext(), "按钮1点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(getApplicationContext(), "按钮2点击了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @OnLongClick({R.id.btn1, R.id.btn2})
    public boolean longClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn1:
                Toast.makeText(getApplicationContext(), "按钮1长按了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:
                Toast.makeText(getApplicationContext(), "按钮2长按了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
