package com.wangyz.jetpack.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * @author wangyz
 * @time 2020/4/17 15:36
 * @description StudentViewModel
 */
public class StudentViewModel extends ViewModel {

    private final LiveData<PagedList<Student>> listLiveData;

    public StudentViewModel() {
        StudentDataSourceFactory factory = new StudentDataSourceFactory();
        this.listLiveData = new LivePagedListBuilder<Integer, Student>(factory, Config.SIZE).build();
    }

    public LiveData<PagedList<Student>> getListLiveData() {
        return listLiveData;
    }
}
