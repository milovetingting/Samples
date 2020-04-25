package com.wangyz.mvvm.model.network.loader.retrofit;

import com.wangyz.mvvm.Config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author wangyz
 * @time 2020/4/21 17:24
 * @description Api
 */
public interface Api {

    /**
     * 加载首页
     *
     * @param page
     * @return
     */
    @GET(Config.URL_ARTICLE)
    Call<ResponseBody> loadHome(@Path("page") int page);
}
