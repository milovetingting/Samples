package com.wangyz.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2019/5/24 10:10
 * @description MenuManager 部分方法参考:https://www.cnblogs.com/popfisher/p/5608436.html
 */
public class MenuManager implements View.OnLongClickListener {

    private PopupWindow mPopupWindow;

    private CustomMenu mMenu;

    private View mAnchor;

    private int mLayoutId;

    private int mMenuId;

    private MenuListener mListener;

    private List<String> mList = new ArrayList<>();

    private int mPosition;

    private int[] mPos;

    /**
     * 给控件绑定菜单
     *
     * @param anchor   需要绑定菜单的控件
     * @param list     菜单内容项
     * @param listener 回调
     */
    public void registerContextMenu(View anchor, List<String> list,
                                    MenuListener
                                            listener) {
        if (anchor == null) {
            throw new RuntimeException("anchor can not be null");
        }
        if (list == null) {
            throw new RuntimeException("list can not be null");
        }
        mAnchor = anchor;
        mList = list;
        mListener = listener;
        mAnchor.setOnLongClickListener(this);
    }

    /**
     * 给控件绑定菜单
     *
     * @param anchor   需要绑定菜单的控件
     * @param list     菜单内容项
     * @param layoutId 布局ID
     * @param menuId   菜单控件ID
     * @param listener 回调
     */
    public void registerContextMenu(View anchor, List<String> list, int layoutId, int menuId,
                                    MenuListener
                                            listener) {
        if (anchor == null) {
            throw new RuntimeException("anchor can not be null");
        }
        if (list == null) {
            throw new RuntimeException("list can not be null");
        }
        mAnchor = anchor;
        mLayoutId = layoutId;
        mMenuId = menuId;
        mList = list;
        mListener = listener;
        mAnchor.setOnLongClickListener(this);
    }

    /**
     * 解除绑定
     */
    public void unregisterContextMenu() {
        mPopupWindow = null;
        mAnchor = null;
        mListener = null;
        mList.clear();
        mList = null;
    }

    /**
     * 展示PopupWindow
     */
    private void showPopupWindow() {
        if (mPopupWindow == null) {
            View contentView;
            if (mLayoutId != 0) {
                contentView = LayoutInflater.from(mAnchor.getContext().getApplicationContext())
                        .inflate(mLayoutId,
                                null);
            } else {
                contentView = LayoutInflater.from(mAnchor.getContext().getApplicationContext())
                        .inflate(R.layout
                                        .popup_menu,
                                null);
            }
            if (mMenuId != 0) {
                mMenu = contentView.findViewById(mMenuId);
            } else {
                mMenu = contentView.findViewById(R.id.dialog_custom_menu);
            }

            mMenu.setList(mList);
            mMenu.setSelected(mPosition);
            mMenu.setListener(mListener);

            mPos = calculatePopWindowPos(mAnchor, contentView);

            mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.showAtLocation(mAnchor, Gravity.TOP | Gravity.START, mPos[0], mPos[1]);
        } else {
            mMenu.setSelected(mPosition);
            mPopupWindow.showAtLocation(mAnchor, Gravity.TOP | Gravity.START, mPos[0], mPos[1]);
        }

    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int[] windowPos = new int[2];
        final int[] anchorLoc = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 获取屏幕高度(px)
     */
    private int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    private int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public boolean onLongClick(View v) {
        showPopupWindow();
        if (mListener != null) {
            mListener.onLongClick();
        }
        return false;
    }

    /**
     * 设置Item为选中状态
     *
     * @param position
     */
    public void setSelection(int position) {
        mPosition = position;
        if (mMenu != null) {
            mMenu.setSelected(mPosition);
        }
    }

    /**
     * Menu的监听回调
     */
    public interface MenuListener {

        /**
         * 长按时的回调
         */
        void onLongClick();

        /**
         * Item单击时的回调
         *
         * @param parent
         * @param view
         * @param position
         * @param id
         */
        void onItemClick(AdapterView<?> parent, View view, int position, long id);

        /**
         * Item长按时的回调
         *
         * @param parent
         * @param view
         * @param position
         * @param id
         * @return
         */
        boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                id);
    }

}
