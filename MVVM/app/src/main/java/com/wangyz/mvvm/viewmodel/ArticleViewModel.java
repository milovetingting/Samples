package com.wangyz.mvvm.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.just.agentweb.AgentWeb;
import com.wangyz.mvvm.databinding.FragmentArticleBinding;

/**
 * @author wangyz
 * @time 2020/4/22 16:19
 * @description ArticleViewModel
 */
public class ArticleViewModel extends ViewModel {

    public View view;

    public FragmentArticleBinding binding;

    public AgentWeb agentWeb;

    public String url;

}
