package com.wangyz.jetpack.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyz
 * @time 2020/4/14 14:55
 * @description LiveDataBus
 */
public class LiveDataBus {

    private Map<String, BusLiveData<Object>> bus;

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return LiveDataBusHolder.INSTANCE;
    }

    public synchronized <T> BusLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new BusLiveData<>());
        }
        return (BusLiveData<T>) bus.get(key);
    }

    static class LiveDataBusHolder {
        static LiveDataBus INSTANCE = new LiveDataBus();
    }

    public static class BusLiveData<T> extends MutableLiveData<T> {

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            hook(observer);
        }

        /**
         * 反射修改mLastVersion和mVersion，使它们相等
         *
         * @param observer
         */
        private void hook(Observer<? super T> observer) {
            try {
                //1.得到mLastVersion
                //获取到LiveData的类中的mObservers对象
                Class<LiveData> liveDataClass = LiveData.class;
                Field mObserversField = liveDataClass.getDeclaredField("mObservers");
                mObserversField.setAccessible(true);
                //获取到这个成员变量的对象
                Object mObserversObject = mObserversField.get(this);
                //得到map对应的class对象
                Class<?> mObserversClass = mObserversObject.getClass();
                //获取到mObservers对象的get方法
                Method get = mObserversClass.getDeclaredMethod("get", Object.class);
                get.setAccessible(true);
                //执行get方法
                Object invokeEntry = get.invoke(mObserversObject, observer);
                //定义一个空的对象
                Object observerWraper = null;
                if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
                    observerWraper = ((Map.Entry) invokeEntry).getValue();
                }
                if (observerWraper == null) {
                    throw new NullPointerException("observerWraper is null");
                }
                //得到ObserverWrapper的类对象  编译擦除问题会引起多态冲突所以用getSuperclass
                Class<?> superclass = observerWraper.getClass().getSuperclass();
                Field mLastVersion = superclass.getDeclaredField("mLastVersion");
                mLastVersion.setAccessible(true);
                //2.得到mVersion
                Field mVersion = liveDataClass.getDeclaredField("mVersion");
                mVersion.setAccessible(true);
                //3.把mVersion的数据填入到mLastVersion中
                Object mVersionValue = mVersion.get(this);
                mLastVersion.set(observerWraper, mVersionValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
