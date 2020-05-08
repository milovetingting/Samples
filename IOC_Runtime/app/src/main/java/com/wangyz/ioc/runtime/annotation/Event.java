package com.wangyz.ioc.runtime.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangyz
 * @time 2020/5/8 9:50
 * @description 事件的注解
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {

    /**
     * 设置listener的方法，如:setOnClickListener
     *
     * @return
     */
    String listenerSetter();

    /**
     * 事件，如:new View.OnClickListener()
     *
     * @return
     */
    Class<?> listenerType();
}
