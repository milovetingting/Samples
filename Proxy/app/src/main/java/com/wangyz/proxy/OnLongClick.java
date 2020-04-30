package com.wangyz.proxy;

import android.view.View;

import androidx.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangyz
 * @time 2020/4/29 8:57
 * @description OnClick
 */
@EventType(listenerType = View.OnLongClickListener.class, listenerSetter = "setOnLongClickListener")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnLongClick {
    @IdRes int[] value();
}
