package com.sppe.shrimppaste.ui;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by @author WHF on 2017/11/8.
 */

public class FooterHelp {

    private final int TOUCH_SLOP = 15;

    private boolean isMoveToUp = false;
    private boolean scrollToFooterTag;


    public void attachScrollToFooterListener(RecyclerView recyclerView,
                                             final ScrollToFooterListener scrollToFooterListener) {
        setOnScrollListener(recyclerView, scrollToFooterListener);
        setOnTouchListener(recyclerView);
    }

    public void resetScrollToFooterTag() {
        scrollToFooterTag = false;
    }

    private void setOnScrollListener(RecyclerView recyclerView, final ScrollToFooterListener scrollToFooterListener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                boolean isScrollToFooter
                        = (newState == RecyclerView.SCROLL_STATE_IDLE)
                        && isMoveToUp
                        && (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                        >= recyclerView.computeVerticalScrollRange())
                        && scrollToFooterListener != null
                        && !scrollToFooterTag;

                if (isScrollToFooter) {
                    scrollToFooterTag = true;
                    scrollToFooterListener.scrollToFooter();
                }
            }
        });
    }

    private void setOnTouchListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                float downY = 0;
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = e.getRawY();
                        isMoveToUp = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if ((e.getRawY() - downY) < -TOUCH_SLOP) {
                            isMoveToUp = true;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    public interface ScrollToFooterListener {
        void scrollToFooter();
    }
}
