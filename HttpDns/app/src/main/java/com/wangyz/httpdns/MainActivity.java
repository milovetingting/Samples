package com.wangyz.httpdns;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        request();
    }

    private void request() {
        OkHttpClient client = new OkHttpClient.Builder().dns(new AliDns(getApplicationContext())).build();
        Retrofit retrofit = new Retrofit.Builder().client(client).baseUrl("https://www.wanandroid.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<Bean> call = api.getBanner();
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if (!response.isSuccessful()) {
                    tv.setText("请求失败，错误码:" + response.code());
                    return;
                }
                Bean bean = response.body();
                tv.setText(bean.toString());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
}