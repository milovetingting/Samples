package com.wangyz.customview.custom;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyz
 * @time 2020/5/12 14:31
 * @description FlowLayout
 */
public class CustomFlowLayout extends ViewGroup {

    private List<View> lineViews;

    private List<List<View>> views;

    private List<Integer> heights;

    private int measureHeight;

    private int realHeight;

    private boolean canScroll;

    private int touchSlop;

    private int lastInterceptX;

    private int lastInterceptY;

    private int lastY;

    private OverScroller scroller;

    private VelocityTracker velocityTracker;

    private int minVelocity;

    private int maxVelocity;

    private int overScrollDistance;

    public CustomFlowLayout(Context context) {
        this(context, null);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        touchSlop = viewConfiguration.getScaledTouchSlop();
        scroller = new OverScroller(context);
        velocityTracker = VelocityTracker.obtain();
        minVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        overScrollDistance = viewConfiguration.getScaledOverscrollDistance();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureHeight = heightSize;

        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;

        init();

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (lineWidth + childWidth > widthSize) {
                views.add(lineViews);
                lineViews = new ArrayList<>();
                width = Math.max(width, lineWidth);
                height += lineHeight;
                heights.add(lineHeight);
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(child);
            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight, childHeight);
            if (i == childCount - 1) {
                height += lineHeight;
                width = Math.max(width, lineWidth);
                heights.add(lineHeight);
                views.add(lineViews);
            }
        }
        realHeight = height;
        canScroll = realHeight > measureHeight;
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = views.size();
        int currX = 0;
        int currY = 0;
        for (int i = 0; i < lineCount; i++) {
            List<View> lineViews = this.views.get(i);
            Integer lineHeight = heights.get(i);
            int size = lineViews.size();
            for (int j = 0; j < size; j++) {
                View child = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int left = currX + lp.leftMargin;
                int top = currY + lp.topMargin;
                int right = left + child.getMeasuredWidth();
                int bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                currX += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            currY += lineHeight;
            currX = 0;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int interceptX = (int) ev.getX();
        int interceptY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastInterceptX = interceptX;
                lastInterceptY = interceptY;
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = interceptX - lastInterceptX;
                int dy = interceptY - lastInterceptY;
                if (Math.abs(dy) > Math.abs(dx) && Math.abs(dy) > touchSlop) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        lastInterceptX = interceptX;
        lastInterceptY = interceptY;
        return intercepted;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!canScroll) {
            return super.onTouchEvent(event);
        }
        velocityTracker.addMovement(event);
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                lastY = touchY;
                break;
            case MotionEvent.ACTION_MOVE:
                int dy = lastY - touchY;

                //使用scrollTo方法实现
                /*int scrollY = getScrollY() + dy;
                if (scrollY < 0) {
                    scrollY = 0;
                }
                if (scrollY > realHeight - measureHeight) {
                    scrollY = realHeight - measureHeight;
                }
                scrollTo(0, scrollY);*/
                //使用scrollTo方法实现

                //使用Scroller实现
                /*if (getScrollY() + dy < 0) {
                    dy = -getScrollY();
                }
                if (getScrollY() + dy > realHeight - measureHeight) {
                    dy = realHeight - measureHeight - getScrollY();
                }
                scroller.startScroll(0, scroller.getFinalY(), 0, dy);*/
                //使用Scroller实现

                //使用OverScroll实现
                scroller.startScroll(0, scroller.getFinalY(), 0, dy);
                //使用OverScroll实现

                invalidate();
                lastY = touchY;
                break;
            case MotionEvent.ACTION_UP:
                velocityTracker.computeCurrentVelocity(1000, maxVelocity);
                int velocityY = (int) velocityTracker.getYVelocity();
                if (Math.abs(velocityY) > minVelocity) {
                    flying(-velocityY);
                } else if (scroller.springBack(getScrollX(), getScrollY(), 0, 0, 0, realHeight - measureHeight)) {
                    postInvalidateOnAnimation();
                }
                break;
            default:
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void flying(int velocityY) {
        if (getChildCount() <= 0) {
            return;
        }
        scroller.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, 0, Math.max(0, realHeight - measureHeight), 0, overScrollDistance);
        postInvalidateOnAnimation();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            //使用Scroller实现
            /*int currY = scroller.getCurrY();
            if (currY < 0) {
                currY = 0;
            }
            if (currY > realHeight - measureHeight) {
                currY = realHeight - measureHeight;
            }
            scrollTo(0, currY);*/
            //使用Scroller实现

            //使用OverScroller实现
            scrollTo(0, scroller.getCurrY());
            //使用OverScroller实现

            invalidate();
        }
    }

    private void init() {
        lineViews = new ArrayList<>();
        views = new ArrayList<>();
        heights = new ArrayList<>();
    }
}
