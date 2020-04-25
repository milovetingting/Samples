package com.wangyz.jetpack.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author wangyz
 * @time 2020/4/15 15:04
 * @description DataBase
 */
@Database(entities = {Student.class, Address.class, School.class}, version = 1)
public abstract class DataBase extends RoomDatabase {

    public abstract StudentDao studentDao();

    public abstract AddressDao addressDao();
}
