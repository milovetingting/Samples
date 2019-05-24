package com.wangyz.menu;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.wangyz.library.MenuManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 */
public class MainActivity extends Activity {

    private static final String TAG = "Menu";

    private TextView mTitle;

    private MenuManager mMenuManager;

    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = findViewById(R.id.title);

        mList.add("操作1");
        mList.add("操作2");
        mList.add("操作3");
        mList.add("操作4");

        mMenuManager = new MenuManager();
        mMenuManager.setSelection(1);
        mMenuManager.registerContextMenu(mTitle, mList , new MenuManager.MenuListener() {

            @Override
            public void onLongClick() {
                Log.i(TAG, "onLongClick");
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick");
                mMenuManager.setSelection(position);
            }

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                    id) {
                Log.i(TAG, "onItemLongClick");
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMenuManager.unregisterContextMenu();
    }
}
