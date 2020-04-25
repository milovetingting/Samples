package com.wangyz.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author wangyz
 * @time 2020/4/15 17:13
 * @description School
 */
@Entity
public class School {

    @PrimaryKey
    public int schoolId;

    @ColumnInfo(name = "schoolName")
    public String schoolName;

    public School(int schoolId, String schoolName) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
