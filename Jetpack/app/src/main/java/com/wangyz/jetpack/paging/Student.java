package com.wangyz.jetpack.paging;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

/**
 * @author wangyz
 * @time 2020/4/17 15:19
 * @description Student
 */
public class Student {

    private String id;

    private String name;

    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return id.equals(student.id) &&
                name.equals(student.name) &&
                gender.equals(student.gender);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender);
    }
}
