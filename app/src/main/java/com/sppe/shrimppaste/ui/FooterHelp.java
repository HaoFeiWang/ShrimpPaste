package com.sppe.shrimppaste.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sppe.shrimppaste.data.contacts.Contacts;

/**
 * Created by @author WHF on 2017/11/8.
 */

public class FooterHelp {

    private static final String TAG = Contacts.LOG_TAG + FooterHelp.class.getSimpleName();
    private static final int TOUCH_SLOP = 15;

    private boolean isMoveToUp;
    private boolean scrollToFooterTag;

    private int itemCount;

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
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean scrollToBottom = (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                        >= recyclerView.computeVerticalScrollRange());
                boolean isScrollToFooter
                        = isMoveToUp
                        && scrollToBottom
                        && !scrollToFooterTag
                        && scrollToFooterListener != null;

                if (isScrollToFooter) {
                    scrollToFooterTag = true;
                    scrollToFooterListener.scrollToFooter();
                } else {
                    Log.i(TAG, "isMoveToUp = " + isMoveToUp + "; scrollToBottom = "
                            + scrollToBottom + "; scrollToFooterTag = " + scrollToFooterTag);

                    Log.i(TAG, "computeVerticalScrollExtent = " + recyclerView.computeVerticalScrollExtent()
                            + "computeVerticalScrollOffset = " + recyclerView.computeVerticalScrollOffset()
                            + "computeVerticalScrollRange = " + recyclerView.computeVerticalScrollRange());
                }
            }
        });
    }

    private void setOnTouchListener(RecyclerView recyclerView) {
        itemCount = recyclerView.getAdapter().getItemCount();

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float downY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = event.getRawY();
                        isMoveToUp = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if ((downY - event.getRawY()) > TOUCH_SLOP) {
                            isMoveToUp = true;
                        }
                        downY = event.getRawY();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public interface ScrollToFooterListener {
        /**
         * 滑动到列表底部
         */
        void scrollToFooter();
    }
}
