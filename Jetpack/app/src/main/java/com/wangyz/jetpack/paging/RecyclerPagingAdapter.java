package com.wangyz.jetpack.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wangyz.jetpack.R;

/**
 * @author wangyz
 * @time 2020/4/17 15:45
 * @description RecyclerPagingAdapter
 */
public class RecyclerPagingAdapter extends PagedListAdapter<Student, RecyclerPagingAdapter.RecyclerViewHolder> {

    private static DiffUtil.ItemCallback<Student> DIFF_STUDENT = new DiffUtil.ItemCallback<Student>() {
        @Override
        public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RecyclerPagingAdapter() {
        super(DIFF_STUDENT);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paging, null);不能在宽度上满屏
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paging, parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Student student = getItem(position);
        if (student == null) {
            holder.tvId.setText("加载中");
            holder.tvName.setText("加载中");
            holder.tvGender.setText("加载中");
        } else {
            holder.tvId.setText(student.getId());
            holder.tvName.setText(student.getName());
            holder.tvGender.setText(student.getGender());
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;
        TextView tvGender;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id);
            tvName = itemView.findViewById(R.id.name);
            tvGender = itemView.findViewById(R.id.gender);
        }
    }

}
