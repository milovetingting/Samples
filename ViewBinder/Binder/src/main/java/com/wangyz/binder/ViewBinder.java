package com.wangyz.binder;

/**
 * @author wangyz
 * @time 2020/3/3 9:46
 * @description ViewBinder
 */
public class ViewBinder {
    public static void bind(Object activity) {
        String name = activity.getClass().getName() + "_ViewBinding";
        try {
            Class<?> clazz = Class.forName(name);
            IBinder binder = (IBinder) clazz.newInstance();
            binder.bind(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
