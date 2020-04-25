package com.wangyz.jetpack.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/15 17:17
 * @description SchoolDao
 */
@Dao
public interface SchoolDao {
    @Insert
    void insert(School... schools);

    @Delete
    void delete(School school);

    @Update
    void update(School school);

    @Query("select * from school")
    List<School> getAll();

    @Query("select * from school where name like :name")
    List<School> findByName(String name);

    @Query("select * from school where schoolId in (:ids)")
    List<School> findByIds(int[] ids);
}
