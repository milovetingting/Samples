package com.wangyz.mvvm.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.just.agentweb.AgentWeb;
import com.wangyz.mvvm.R;
import com.wangyz.mvvm.bean.Article;
import com.wangyz.mvvm.viewmodel.ArticleViewModel;
import com.wangyz.mvvm.viewmodel.BottomViewModel;
import com.wangyz.mvvm.viewmodel.ViewModelBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {


    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        ArticleViewModel viewModel = ViewModelBus.getInstance().get(ArticleViewModel.class);
        if (viewModel != null && viewModel.agentWeb != null) {
            viewModel.agentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        ArticleViewModel viewModel = ViewModelBus.getInstance().get(ArticleViewModel.class);
        if (viewModel != null && viewModel.agentWeb != null) {
            viewModel.agentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        ArticleViewModel viewModel = ViewModelBus.getInstance().get(ArticleViewModel.class);
        if (viewModel != null && viewModel.agentWeb != null) {
            viewModel.agentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!ViewModelBus.getInstance().exist(ArticleViewModel.class)) {
            View view = inflater.inflate(R.layout.fragment_article, container, false);
            ArticleViewModel viewModel = ViewModelBus.getInstance().provide(this, ArticleViewModel.class);
            viewModel.view = view;
            viewModel.binding = DataBindingUtil.bind(view);
            viewModel.url = getArguments().getString("url");
            viewModel.agentWeb = AgentWeb.with(this).setAgentWebParent(viewModel.binding.articleContainer, new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().createAgentWeb().ready().go(viewModel.url);
            viewModel.agentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true);
            viewModel.agentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true);
            String title = getArguments().getString("title");
            viewModel.binding.setVariable(com.wangyz.mvvm.BR.title, title);
            return viewModel.view;
        } else {
            ArticleViewModel viewModel = ViewModelBus.getInstance().get(ArticleViewModel.class);
            viewModel.url = getArguments().getString("url");
            viewModel.agentWeb = AgentWeb.with(this).setAgentWebParent(viewModel.binding.articleContainer, new LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().createAgentWeb().ready().go(viewModel.url);
            String title = getArguments().getString("title");
            viewModel.binding.setVariable(com.wangyz.mvvm.BR.title, title);
        }
        return ViewModelBus.getInstance().get(ArticleViewModel.class).view;
    }

    public static class ClickProxy {
        public void openArticle(View view, Article article) {
            Bundle bundle = new Bundle();
            bundle.putString("title", article.title);
            bundle.putString("url", article.url);
            Navigation.findNavController(view).navigate(R.id.articleFragment, bundle);
            ViewModelBus.getInstance().get(BottomViewModel.class).view.setVisibility(View.GONE);
        }
    }

}
