package com.wangyz.jetpack.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/15 14:59
 * @description StudentDao
 */
@Dao
public interface StudentDao {

    @Insert
    void insert(Student... students);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Query("select * from student")
    List<Student> getAll();

    @Query("select * from student where name like :name")
    List<Student> findByName(String name);

    @Query("select * from student where id in (:ids)")
    List<Student> findByIds(int[] ids);

    @Query("select id,name from student")
    List<StudentTuple> getTuple();

    @Query("select s.* ,a.address from student as s inner join address as a where s.addressId = a.aid")
    List<StudentWithAddress> getStudentInfo();

}
