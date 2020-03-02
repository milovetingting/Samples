package com.wangyz.loader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangyz.loader.bean.Data;
import com.wangyz.loader.callback.HttpCallback;
import com.wangyz.loader.helper.HttpHelper;
import com.wangyz.loader.helper.ImageHelper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String URL_HTTP = "https://www.wanandroid.com/wxarticle/chapters/json";

    private static final String URL_IMAGE = "https://timgsa.baidu" +
            ".com/timg?image&quality=80&size=b9999_10000&sec=1583146828329&di" +
            "=7925410918331d79cfe67dd52f85049d&imgtype=0&src=http%3A%2F%2Fwww.cssxt" +
            ".com%2Fuploadfile%2F2016%2F0912%2F20160912050556889.jpg";

    private TextView mTv;

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTv = findViewById(R.id.tv);
        mIv = findViewById(R.id.iv);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.obtain().get(URL_HTTP, null, new HttpCallback<Data>() {
                    @Override
                    public void onFailed(final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTv.setText(msg);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final Data data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTv.setText(data.toString());
                            }
                        });
                    }
                });
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTv.setText("暂未实现");
            }
        });

        findViewById(R.id.btn_load_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageHelper.obtain().load(URL_IMAGE, mIv);
            }
        });

    }
}
