package com.wangyz.httpdns;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("banner/json")
    Call<Bean> getBanner();
}
