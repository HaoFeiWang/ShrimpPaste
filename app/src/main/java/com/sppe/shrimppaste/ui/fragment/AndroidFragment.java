package com.sppe.shrimppaste.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.adapter.AndroidAdapter;
import com.sppe.shrimppaste.adapter.AndroidItemDecoration;
import com.sppe.shrimppaste.adapter.FooterAdapterWrapper;
import com.sppe.shrimppaste.adapter.FooterRecyclerScrollListener;
import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.adapter.GirlItemDecoration;
import com.sppe.shrimppaste.base.BaseMvpFragment;
import com.sppe.shrimppaste.base.BaseMvpPresent;
import com.sppe.shrimppaste.data.contacts.Contacts;
import com.sppe.shrimppaste.data.dao.GankEntry;
import com.sppe.shrimppaste.present.AndroidPresent;
import com.sppe.shrimppaste.ui.activity.PhotoActivity;
import com.sppe.shrimppaste.ui.view.AndroidView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class AndroidFragment extends BaseMvpFragment<AndroidView,AndroidPresent> implements AndroidView {

    private static final int START_PAGE = 0;

    private RecyclerView recyclerView;
    private ArrayList<GankEntry> gankEntryList;
    private AndroidAdapter adapter;
    private FooterAdapterWrapper footerAdapter;
    private int currentPage;
    private SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_android);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_android);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {

        gankEntryList = new ArrayList<>();
        adapter = new AndroidAdapter(getContext(), gankEntryList);
        setOnItemClickListener(adapter);
        footerAdapter = new FooterAdapterWrapper(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(footerAdapter);
        recyclerView.addItemDecoration(new AndroidItemDecoration(getContext()));
        setScrollListener();

        present.refreshDataFromDb();
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                present.refreshDataFromNet(START_PAGE);
            }
        });
    }

    private void setScrollListener() {
        recyclerView.addOnScrollListener(new FooterRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                footerAdapter.setCurrentState(FooterAdapterWrapper.STATE_LOADING);
                footerAdapter.notifyDataSetChanged();
                present.refreshDataFromNet(++currentPage);
            }
        });
    }

    @Override
    public AndroidPresent createPresent() {
        return new AndroidPresent(getContext());
    }

    @Override
    public void refreshData(List<GankEntry> gankEntryList) {
        this.gankEntryList = (ArrayList<GankEntry>) gankEntryList;

        adapter.setDateList(gankEntryList);
        setOnItemClickListener(adapter);
        footerAdapter.setCurrentState(FooterAdapterWrapper.STATE_COMPLETE);
        footerAdapter.setAdapter(adapter);
        footerAdapter.notifyDataSetChanged();
        Log.e("====", "刷新数据");
        if (gankEntryList.size() == 0) {
            present.refreshDataFromNet(currentPage);
        }
    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }

    public void setOnItemClickListener(AndroidAdapter adapter) {
        adapter.setOnItemClickListener(new AndroidAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                GankEntry gankEntry = gankEntryList.get(position);

                Intent intent = new Intent(getContext(), PhotoActivity.class);
                intent.putExtra(Contacts.BUNDLE_RUL, gankEntry.getUrl());

                startActivity(intent);

            }
        });
    }
}
