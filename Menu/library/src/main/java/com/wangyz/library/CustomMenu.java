package com.wangyz.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2019/5/23 17:09
 * @description CustomMenu
 */
public class CustomMenu extends LinearLayout {

    private TextView mHeader;

    private View mDivider;

    private ListView mList;

    private String mHeadText;

    private int mHeaderColor;

    private int mHeaderBg;

    private int mHeaderHeight;

    private int mDividerBg;

    private int mItemBg;

    private int mItemPadding;

    private int mItemColor;

    private int mItemHeight;

    private CustomAdapter mAdapter;

    private List<String> mDataList = new ArrayList<>();

    private MenuManager.MenuListener mListener;

    public CustomMenu(Context context) {
        this(context, null);
    }

    public CustomMenu(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomMenu,
                defStyleAttr, 0);
        mHeadText = ta.getString(R.styleable.CustomMenu_header_text);
        mHeaderColor = ta.getColor(R.styleable.CustomMenu_header_text_color, getResources()
                .getColor(R.color
                        .custom_menu_head_color, null));
        mHeaderBg = ta.getColor(R.styleable.CustomMenu_header_bg, getResources().getColor(R.color
                .custom_menu_head_bg, null));
        mHeaderHeight = (int) ta.getDimension(R.styleable.CustomMenu_header_height, getResources()
                .getDimension(R.dimen.custom_menu_header_height));
        mDividerBg = ta.getResourceId(R.styleable.CustomMenu_divider_bg, R.drawable.item_divider);
        mItemBg = ta.getResourceId(R.styleable.CustomMenu_item_bg, R.drawable.item_bg);
        mItemPadding = (int) ta.getDimension(R.styleable.CustomMenu_item_paddingStart,
                getResources()
                        .getDimension(R.dimen.custom_menu_item_paddingStart));
        mItemColor = ta.getColor(R.styleable.CustomMenu_item_textColor, getResources()
                .getColor(R.color
                        .menu, null));
        mItemHeight = (int) ta.getDimension(R.styleable.CustomMenu_item_height,
                getResources()
                        .getDimension(R.dimen.custom_menu_item_height));

        ta.recycle();

        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_menu, this, true);
        mHeader = rootView.findViewById(R.id.custom_menu_header);
        mDivider = rootView.findViewById(R.id.custom_menu_divider);
        mList = rootView.findViewById(R.id.custom_menu_list);

        mHeader.setText(mHeadText);
        mHeader.setTextColor(mHeaderColor);
        mHeader.setBackgroundColor(mHeaderBg);
        mHeader.setHeight(mHeaderHeight);

        mDivider.setBackgroundResource(mDividerBg);


        mAdapter = new CustomAdapter(mDataList, context);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onItemClick(parent, view, position, id);
                }
            }
        });
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long
                    id) {
                if (mListener != null) {
                    mListener.onItemLongClick(parent, view, position, id);
                }
                return false;
            }
        });
        mList.setAdapter(mAdapter);
    }

    /**
     * 设置头部文字
     *
     * @param text
     */
    public void setHeader(String text) {
        mHeader.setText(text);
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public void setList(List<String> list) {
        mAdapter.setList(list);
    }

    /**
     * 设置Listener
     *
     * @param listener
     */
    public void setListener(MenuManager.MenuListener listener) {
        mListener = listener;
    }

    /**
     * 设置Item选中
     *
     * @param position
     */
    public void setSelected(int position) {
        mAdapter.setSelected(position);
    }

    class CustomAdapter extends BaseAdapter {

        private List<String> mList;

        private Context mContext;

        private int mSelectedPosition;

        public CustomAdapter(List<String> list, Context context) {
            this.mList = list;
            this.mContext = context;
        }

        public void setList(List<String> list) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }

        public void setSelected(int position) {
            mSelectedPosition = position;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_menu_item,
                        null);
                holder.mItem = convertView.findViewById(R.id.custom_menu_item);
                holder.mTitle = convertView.findViewById(R.id.custom_menu_item_title);
                holder.mItem.setBackgroundResource(mItemBg);
                holder.mTitle.setPadding(mItemPadding, 0, 0, 0);
                holder.mTitle.setTextColor(mItemColor);
                holder.mTitle.setHeight(mItemHeight);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTitle.setText(mList.get(position));
            if (mSelectedPosition == position) {
                holder.mItem.setBackgroundResource(R.drawable.item_selected);
                holder.mTitle.setTextColor(Color.WHITE);
            } else {
                holder.mItem.setBackgroundResource(R.drawable.item_normal);
                holder.mTitle.setTextColor(Color.BLACK);
            }
            return convertView;
        }
    }

    class ViewHolder {
        public LinearLayout mItem;
        public TextView mTitle;
    }

}
