package com.wangyz.commonmvvm.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.wangyz.commonmvvm.R;
import com.wangyz.commonmvvm.base.BaseActivity;
import com.wangyz.commonmvvm.databinding.ActivityArticleBinding;

/**
 * @author wangyz
 * @time 2019/7/4 15:41
 * @description ArticleActivity
 */
public class ArticleActivity extends BaseActivity<ActivityArticleBinding> {

    private AgentWeb mAgentWeb;
    private String mLink;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mLink = getIntent().getStringExtra("link");
        mAgentWeb = AgentWeb.with(this).setAgentWebParent(viewDataBinding.container, new
                LinearLayout.LayoutParams(-1, -1)).useDefaultIndicator().createAgentWeb().ready()
                .go(mLink);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true);
    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
