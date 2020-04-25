package com.wangyz.jetpack.paging;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2020/4/17 15:24
 * @description StudentDataSource
 */
public class StudentDataSource extends PositionalDataSource<Student> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Student> callback) {
        callback.onResult(getStudents(0, Config.SIZE), 0, 1000);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Student> callback) {
        callback.onResult(getStudents(params.startPosition, params.loadSize));
    }

    private List<Student> getStudents(int startPosition, int pageSize) {
        List<Student> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            Student student = new Student();
            student.setId("ID:" + i);
            student.setName("名称:" + i);
            student.setGender("性别:" + i);
            list.add(student);
        }
        return list;
    }
}
