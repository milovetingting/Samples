package com.wangyz.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author wangyz
 * @time 2020/4/13 16:01
 * @description LiveDataSub
 */
public class LiveDataSub extends ViewModel {

    private MutableLiveData<String> infos;

    private int number;

    public MutableLiveData<String> getInfo() {
        if (infos == null) {
            infos = new MutableLiveData<>();
        }
        return infos;
    }

    public int increaseNumber() {
        number++;
        return number;
    }
}
