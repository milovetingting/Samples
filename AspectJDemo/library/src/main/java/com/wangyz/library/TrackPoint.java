package com.wangyz.library;

/**
 * @author wangyz
 * @time 2020/1/13 10:55
 * @description TrackPoint
 */
public class TrackPoint {

    private static TrackCallBack mTrackCallBack;

    private TrackPoint() {

    }

    /**
     * 初始化
     * @param trackCallBack
     */
    public static void init(TrackCallBack trackCallBack) {
        mTrackCallBack = trackCallBack;
    }

    static void onClick(String pageName, String viewIdName) {
        if (mTrackCallBack == null) {
            return;
        }
        mTrackCallBack.onClick(pageName, viewIdName);
    }

    static void onPageOpen(String pageName) {
        if (mTrackCallBack == null) {
            return;
        }
        mTrackCallBack.onPageOpen(pageName);
    }

    static void onPageClose(String pageName) {
        if (mTrackCallBack == null) {
            return;
        }
        mTrackCallBack.onPageClose(pageName);
    }

}
