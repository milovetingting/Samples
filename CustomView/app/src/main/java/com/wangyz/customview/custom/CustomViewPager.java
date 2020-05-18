package com.wangyz.customview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import androidx.annotation.RequiresApi;

import com.wangyz.customview.R;

/**
 * @author wangyz
 * @time 2020/5/14 15:07
 * @description CustomViewPager
 */
public class CustomViewPager extends ViewGroup {

    private static final int ORIENTATION_HORIZONTAL = 1;

    private static final int ORIENTATION_VERTICAL = 2;

    private int orientation = ORIENTATION_HORIZONTAL;

    private int pageWidth;

    private int pageHeight;

    private int realWidth;

    private int realHeight;

    private int measureWidth;

    private int measureHeight;

    private int lastInterceptX;

    private int lastInterceptY;

    private int lastDownX;

    private int lastDownY;

    private int lastTouchX;

    private int lastTouchY;

    private int touchSlop;

    private OverScroller scroller;

    private VelocityTracker velocityTracker;

    private int minVelocity;

    private int maxVelocity;

    private int overScrollDistance;

    private int currentPage;

    private int pendingPage = -1;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);
        orientation = ta.getInt(R.styleable.CustomViewPager_orientation, ORIENTATION_HORIZONTAL);
        ta.recycle();

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        touchSlop = viewConfiguration.getScaledPagingTouchSlop();
        scroller = new OverScroller(context);
        velocityTracker = VelocityTracker.obtain();
        minVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        maxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        overScrollDistance = viewConfiguration.getScaledOverscrollDistance();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureWidth = widthSize;
        measureHeight = heightSize;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            pageWidth = Math.max(pageWidth, childWidth);
            pageHeight = Math.max(pageHeight, childHeight);
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            pageWidth = widthSize;
        }
        int width;
        if (orientation == ORIENTATION_HORIZONTAL) {
            width = childCount * pageWidth;
        } else {
            width = pageWidth;
        }
        int height;
        if (heightMode == MeasureSpec.EXACTLY) {
            pageHeight = heightSize;
        }
        if (orientation == ORIENTATION_HORIZONTAL) {
            height = pageHeight;
        } else {
            height = childCount * pageHeight;
        }
        realWidth = width;
        realHeight = height;
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (orientation == ORIENTATION_HORIZONTAL) {
                child.layout(i * pageWidth, 0, i * pageWidth + child.getMeasuredWidth(), child.getMeasuredHeight());
            } else {
                child.layout(0, i * pageHeight, child.getMeasuredWidth(), i * pageHeight + child.getMeasuredHeight());
            }
        }
        if (childCount > 0 && pendingPage != -1) {
            setCurrentPage(pendingPage);
            pendingPage = -1;
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
                if (orientation == ORIENTATION_HORIZONTAL) {
                    if (Math.abs(dx) > Math.abs(dy) && Math.abs(dx) > touchSlop) {
                        intercepted = true;
                    } else {
                        intercepted = false;
                    }
                } else {
                    if (Math.abs(dy) > Math.abs(dx) && Math.abs(dy) > touchSlop) {
                        intercepted = true;
                    } else {
                        intercepted = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;
        }
        return intercepted;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getChildCount() <= 1) {
            return super.onTouchEvent(event);
        }
        velocityTracker.addMovement(event);
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                lastDownX = touchX;
                lastDownY = touchY;
                lastTouchX = touchX;
                lastTouchY = touchY;
                break;
            case MotionEvent.ACTION_MOVE: {
                int dx = lastTouchX - touchX;
                int dy = lastTouchY - touchY;
                if (orientation == ORIENTATION_HORIZONTAL) {
                    if (getScrollX() + dx < -pageWidth / 4) {
                        dx = -pageWidth / 4 - getScrollX();
                    }
                    if (getScrollX() + dx > realWidth - measureWidth + pageWidth / 4) {
                        dx = realWidth - measureWidth + pageWidth / 4 - getScrollX();
                    }
                    scroller.startScroll(scroller.getFinalX(), 0, dx, 0);
                } else {
                    if (getScrollY() + dy < -pageHeight / 4) {
                        dy = -pageHeight / 4 - getScrollY();
                    }
                    if (getScrollY() + dy > realHeight - measureHeight + pageHeight / 4) {
                        dy = realHeight - measureHeight + pageHeight / 4 - getScrollY();
                    }
                    scroller.startScroll(0, scroller.getFinalY(), 0, dy);
                }
                invalidate();
                lastTouchX = touchX;
                lastTouchY = touchY;
            }
            break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                int dx = lastTouchX - lastDownX;
                int dy = lastTouchY - lastDownY;
                velocityTracker.computeCurrentVelocity(1000, maxVelocity);
                int velocityX = (int) velocityTracker.getXVelocity();
                int velocityY = (int) velocityTracker.getYVelocity();
                if (orientation == ORIENTATION_HORIZONTAL) {
                    if (Math.abs(velocityX) > minVelocity) {
                        updateCurrentPage(dx < 0);
                        flying(-velocityX);
                    } else {
                        if (Math.abs(dx) >= pageWidth / 2) {
                            updateCurrentPage(dx < 0);
                            flying(-velocityX);
                        } else {
                            scroller.startScroll(scroller.getFinalX(), 0, dx, 0);
                        }
                    }
                } else {
                    if (Math.abs(velocityY) > minVelocity) {
                        updateCurrentPage(dy < 0);
                        flying(-velocityY);
                    } else {
                        if (Math.abs(dy) >= pageHeight / 2) {
                            updateCurrentPage(dy < 0);
                            flying(-velocityY);
                        } else {
                            scroller.startScroll(0, scroller.getFinalY(), 0, dy);
                        }
                    }
                }
            }
            break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            if (orientation == ORIENTATION_HORIZONTAL) {
                scrollTo(scroller.getCurrX(), 0);
            } else {
                scrollTo(0, scroller.getCurrY());
            }
            invalidate();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void flying(int velocity) {
        if (getChildCount() <= 0) {
            return;
        }
        if (orientation == ORIENTATION_HORIZONTAL) {
            int dx = pageWidth * currentPage;
            scroller.fling(getScrollX(), getScrollY(), velocity, 0, dx, dx, 0, 0, overScrollDistance, 0);
        } else {
            int dy = pageHeight * currentPage;
            scroller.fling(getScrollX(), getScrollY(), 0, velocity, 0, 0, dy, dy, 0, overScrollDistance);
        }
        postInvalidateOnAnimation();
    }

    private void updateCurrentPage(boolean pageIncrease) {
        if (pageIncrease) {
            if (currentPage < getChildCount() - 1) {
                currentPage++;
            }
        } else {
            if (currentPage > 0) {
                currentPage--;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setCurrentPage(int currentPage) {
        if (orientation == ORIENTATION_HORIZONTAL) {
            if (pageWidth == 0) {
                pendingPage = currentPage;
                return;
            }
        } else {
            if (pageHeight == 0) {
                pendingPage = currentPage;
                return;
            }
        }

        if (currentPage >= getChildCount()) {
            return;
        }
        if (currentPage < 0) {
            return;
        }
        int oldPage = this.currentPage;
        this.currentPage = currentPage;
        if (currentPage > oldPage) {
            flying(-100);
        } else {
            flying(100);
        }
    }
}
