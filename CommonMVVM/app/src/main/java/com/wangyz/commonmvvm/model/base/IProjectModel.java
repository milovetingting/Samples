package com.wangyz.commonmvvm.model.base;

import com.wangyz.commonmvvm.bean.entity.Project;

import java.util.List;

/**
 * @author wangyz
 * @time 2019/7/3 17:18
 * @description IProjectModel
 */
public interface IProjectModel {

    /**
     * 加载数据
     *
     * @param page
     * @param callBack
     */
    void load(int page, LoadCallBack callBack);

    /**
     * 加载回调
     */
    interface LoadCallBack {

        /**
         * 加载成功
         *
         * @param data
         */
        void loadSuccess(List<Project> data);

        /**
         * 加载失败
         *
         * @param msg
         */
        void loadFailed(String msg);
    }

}
