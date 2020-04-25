package com.wangyz.mvvm.model.network.loader.retrofit;

import com.wangyz.mvvm.Config;
import com.wangyz.mvvm.model.network.interfaces.IHttpCallback;
import com.wangyz.mvvm.model.network.interfaces.IHttpLoader;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author wangyz
 * @time 2020/4/22 9:18
 * @description RetrofitLoader
 */
public class RetrofitLoader implements IHttpLoader {

    private static final Pattern COMPILE_URL_HOME = Pattern.compile("^" + Config.URL_BASE + Config.URL_ARTICLE.replace("{page}", "(\\w+)") + "$");

    protected Retrofit retrofit;

    protected Api api;

    public RetrofitLoader() {
        retrofit = new Retrofit.Builder().baseUrl(Config.URL_BASE).build();
        api = retrofit.create(Api.class);
    }

    @Override
    public void get(String url, Map<String, Object> params, IHttpCallback callback) {
        Matcher matcher = COMPILE_URL_HOME.matcher(url);
        if (matcher.matches()) {
            try {
                Call<ResponseBody> call = api.loadHome(Integer.valueOf(matcher.group(1)));
                Response<ResponseBody> response = call.execute();
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().string());
                } else {
                    callback.onFailed(response.errorBody().string());
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onFailed(e.getMessage());
            }
        }
    }

    @Override
    public void post(String url, Map<String, Object> params, IHttpCallback callback) {

    }
}
