package com.wangyz.jetpack.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @author wangyz
 * @time 2020/4/14 15:58
 * @description User
 */
public class User extends BaseObservable {

    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.wangyz.jetpack.BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.wangyz.jetpack.BR.age);
    }
}
