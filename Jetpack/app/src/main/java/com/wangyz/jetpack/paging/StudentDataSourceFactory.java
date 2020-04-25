package com.wangyz.jetpack.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

/**
 * @author wangyz
 * @time 2020/4/17 15:34
 * @description StudentDataSourceFactory
 */
public class StudentDataSourceFactory extends DataSource.Factory<Integer, Student> {
    @NonNull
    @Override
    public DataSource<Integer, Student> create() {
        StudentDataSource dataSource = new StudentDataSource();
        return dataSource;
    }
}
