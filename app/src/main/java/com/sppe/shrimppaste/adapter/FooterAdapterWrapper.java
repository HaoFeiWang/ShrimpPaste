package com.sppe.shrimppaste.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;

import java.util.List;


/**
 * 上拉刷新的Adapter的包装类
 * Created by @author WHF on 2017/11/8.
 */

public class FooterAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public static final int STATE_START = 1;
    public static final int STATE_LOADING = 2;
    public static final int STATE_COMPLETE = 3;


    private RecyclerView.Adapter adapter;
    private int currentState;

    public FooterAdapterWrapper(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.currentState = STATE_COMPLETE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 1 && position + 1 >= getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
            return new FooterHolder(view);
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterHolder) {
            if (currentState == STATE_LOADING) {
                ((FooterHolder) holder).itemView.setVisibility(View.VISIBLE);
            }else {
                ((FooterHolder) holder).itemView.setVisibility(View.GONE);
            }
        } else {
            adapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + 1;
    }

    /**
     * 如果是网格或者流布局，则一个Footer占两个单元格
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager manager = (GridLayoutManager) layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_FOOTER ? manager.getSpanCount() : 1;
                }
            });
        }
    }

    public void setCurrentState(int state) {
        this.currentState = state;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

}
