package com.wangyz.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wangyz.mvvm.R;
import com.wangyz.mvvm.bean.Article;
import com.wangyz.mvvm.databinding.ItemHomeBinding;
import com.wangyz.mvvm.view.ArticleFragment;

/**
 * @author wangyz
 * @time 2020/4/21 15:51
 * @description HomeAdapter
 */
public class HomeAdapter extends PagedListAdapter<Article, HomeAdapter.RecyclerViewHolder> {

    private static DiffUtil.ItemCallback<Article> DIFF_STUDENT = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Article oldItem, @NonNull Article newItem) {
            return oldItem.equals(newItem);
        }
    };

    public HomeAdapter() {
        super(DIFF_STUDENT);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Article article = getItem(position);
        if (article != null) {
            holder.binding.setVariable(BR.article, article);
        }
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ItemHomeBinding binding;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.setVariable(BR.click, new ArticleFragment.ClickProxy());
        }
    }
}
