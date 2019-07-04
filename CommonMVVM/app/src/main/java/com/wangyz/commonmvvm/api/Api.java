package com.wangyz.commonmvvm.api;

import com.wangyz.commonmvvm.bean.model.Project;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author wangyz
 * @time 2019/7/3 17:25
 * @description Api
 */
public interface Api {

    /**
     * 加载数据
     *
     * @param page
     * @param cid
     * @return
     */
    @GET("/project/list/{page}/json")
    Observable<Project> load(@Path("page") int page, @Query("cid") int cid);

}
