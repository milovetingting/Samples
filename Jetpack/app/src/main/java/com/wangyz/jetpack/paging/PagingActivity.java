package com.wangyz.jetpack.paging;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/17 15:39
 * @description PagingActivity
 */
public class PagingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerPagingAdapter adapter;
    StudentViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        recyclerView = findViewById(R.id.rv);
        adapter = new RecyclerPagingAdapter();
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(StudentViewModel.class);
        viewModel.getListLiveData().observe(this, new Observer<PagedList<Student>>() {
            @Override
            public void onChanged(PagedList<Student> students) {
                adapter.submitList(students);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
