package com.wangyz.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * @author wangyz
 * @time 2020/4/15 14:43
 * @description Student
 */
@Entity(foreignKeys = @ForeignKey(entity = Address.class, parentColumns = "aid", childColumns = "addressId"))
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public int age;

    @ColumnInfo(name = "addressId")
    public int addressId;

    @Embedded
    public School school;

    public Student() {

    }

    public Student(String name, int age, int addressId) {
        this.name = name;
        this.age = age;
        this.addressId = addressId;
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addressId=" + addressId +
                ", school=" + school +
                '}';
    }
}
