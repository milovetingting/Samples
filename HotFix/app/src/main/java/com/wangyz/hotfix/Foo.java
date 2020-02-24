package com.wangyz.hotfix;

import android.content.Context;
import android.widget.Toast;

/**
 * @author wangyz
 * @time 2020/2/20 8:26
 * @description Foo
 */
public class Foo {

    /**
     * 显示Toast
     *
     * @param context
     * @param text
     */
    public void showToastShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
