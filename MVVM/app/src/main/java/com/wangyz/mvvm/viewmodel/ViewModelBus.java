package com.wangyz.mvvm.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyz
 * @time 2020/4/22 13:59
 * @description ViewModelBus
 */
public class ViewModelBus {

    private Map<String, ViewModel> bus;

    private ViewModelBus() {
        init();
    }

    public static ViewModelBus getInstance() {
        return ViewModelBusHolder.INSTANCE;
    }

    /**
     * 获取ViewModel
     *
     * @param owner
     * @param type
     * @param <T>
     * @return
     */
    public synchronized <T extends ViewModel> T provide(ViewModelStoreOwner owner, Class<T> type) {
        init();
        if (!bus.containsKey(type.getName())) {
            bus.put(type.getName(), new ViewModelProvider(owner, new ViewModelProvider.NewInstanceFactory()).get(type));
        }
        return (T) bus.get(type.getName());
    }

    /**
     * 获取ViewModel
     *
     * @param type
     * @param <T>
     * @return
     */
    public synchronized <T extends ViewModel> T get(Class<T> type) {
        init();
        if (bus.containsKey(type.getName())) {
            return (T) bus.get(type.getName());
        }
        return null;
    }

    /**
     * 是否存在ViewModel
     *
     * @param type
     * @param <T>
     * @return
     */
    public synchronized <T extends ViewModel> boolean exist(Class<T> type) {
        init();
        return bus.containsKey(type.getName());
    }

    /**
     * 释放资源
     */
    public synchronized void release() {
        if (bus != null) {
            bus.clear();
            bus = null;
        }
    }

    private void init() {
        if (bus == null) {
            bus = new HashMap<>(16);
        }
    }

    static class ViewModelBusHolder {
        private static ViewModelBus INSTANCE = new ViewModelBus();
    }

}
