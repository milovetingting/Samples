package com.wangyz.commonmvvm.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @param <B>
 * @author wangyz
 */
public class BaseBindingVH<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    /**
     * viewDataBinding
     */
    private B mBinding;

    /**
     * constructor
     *
     * @param binding viewDataBinding
     */
    public BaseBindingVH(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     * @return viewDataBinding
     */
    public B getBinding() {
        return mBinding;
    }

}
