package com.wangyz.jetpack.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/15 16:54
 * @description AddressDao
 */
@Dao
public interface AddressDao {

    @Insert
    void insert(Address... addresses);

    @Delete
    void delete(Address address);

    @Update
    void update(Address address);

    @Query("select * from address")
    List<Address> getAll();

    @Query("select * from address where address like :name")
    List<Address> findByName(String name);

    @Query("select * from address where aid in (:ids)")
    List<Address> findByIds(int[] ids);

}
