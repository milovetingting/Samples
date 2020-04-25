package com.wangyz.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author wangyz
 * @time 2020/4/15 16:22
 * @description StudentTuple
 */
@Entity
public class StudentTuple {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @Override
    public String toString() {
        return "StudentTuple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
