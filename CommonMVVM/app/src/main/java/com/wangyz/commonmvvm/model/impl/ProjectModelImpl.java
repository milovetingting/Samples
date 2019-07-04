package com.wangyz.commonmvvm.model.impl;

import com.wangyz.commonmvvm.api.Api;
import com.wangyz.commonmvvm.bean.model.Project;
import com.wangyz.commonmvvm.model.base.IProjectModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wangyz
 * @time 2019/7/3 17:22
 * @description ProjectModelImpl
 */
public class ProjectModelImpl implements IProjectModel {
    @Override
    public void load(int page, final LoadCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com")
                .addCallAdapterFactory
                        (RxJava2CallAdapterFactory.create()).addConverterFactory
                        (GsonConverterFactory
                                .create()).build();
        Api api = retrofit.create(Api.class);
        api.load(page, 294).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(new Observer<Project>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Project project) {
                if (project != null && project.getErrorCode() == 0) {
                    List<Project.DataBean.DatasBean> beans = project.getData().getDatas();
                    List<com.wangyz.commonmvvm.bean.entity.Project> list = new ArrayList<>();
                    for (Project.DataBean.DatasBean bean : beans) {
                        com.wangyz.commonmvvm.bean.entity.Project p = new com.wangyz.commonmvvm.bean
                                .entity.Project(bean.getEnvelopePic(), bean.getDesc(), bean
                                .getLink());
                        list.add(p);
                    }
                    callBack.loadSuccess(list);
                }
            }

            @Override
            public void onError(Throwable e) {
                callBack.loadFailed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
