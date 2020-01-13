package com.wangyz.library;

/**
 * @author wangyz
 * @time 2020/1/13 10:54
 * @description TrackCallBack
 */
public interface TrackCallBack {

    /**
     * 当View被点击
     *
     * @param pageName
     * @param viewIdName
     */
    void onClick(String pageName, String viewIdName);

    /**
     * 当页面打开时
     *
     * @param pageName
     */
    void onPageOpen(String pageName);

    /**
     * 当页面关闭时
     *
     * @param pageName
     */
    void onPageClose(String pageName);

}
