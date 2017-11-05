package com.sppe.shrimppaste.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Scroller;

/**
 * Created by WHF on 2017/11/5.
 */

public class RefreshLayout extends SwipeRefreshLayout{

    /**
     * 阻尼系数
     */
    private static final float DAMP_QUOTIETY = 0.3f;
    /**
     * 回弹时间
     */
    private static final int SCROLL_DURATION = 300;
    /**
     * 滚动开始
     */
    private int start;
    /**
     * 上一次滑动的坐标
     */
    private int lastY;
    /**
     * 滚动辅助类
     */
    private Scroller scroller;

    /**
     * 是否拦截
     */
    private boolean isIntercept;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (! scroller.isFinished()) {
                    scroller.abortAnimation();  //终止动画
                }
                scrollTo(0, (int) ((lastY - y) * DAMP_QUOTIETY));
                break;
            case MotionEvent.ACTION_UP:
                int end = getScrollY();
                int scrollY = end - start;
                scroller.startScroll(0, end, 0, - scrollY, SCROLL_DURATION);
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (y - lastY < 0 && isIntercept) {
                    return true;
                }
                break;
        }
        return false;
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY());
            postInvalidate();
        }
    }

    public void setIntercept(boolean intercept) {
        isIntercept = intercept;
    }

}
