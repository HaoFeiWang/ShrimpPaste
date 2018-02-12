package com.sppe.shrimppaste.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sppe.shrimppaste.data.contacts.Contacts;

/**
 * Created by WHF on 2017/11/8.
 */

public abstract class FooterRecyclerScrollListener extends RecyclerView.OnScrollListener {

    private boolean isSlidingUp = false;
    private LinearLayoutManager layoutManager;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        Log.i(Contacts.LOG_TAG, "cur state = " + newState);

        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int currentPosition = layoutManager.getItemCount();
        int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        if (lastPosition == (currentPosition - 1) && isSlidingUp) {
            onLoadMore();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingUp = dy > 0;
    }

    public abstract void onLoadMore();
}
