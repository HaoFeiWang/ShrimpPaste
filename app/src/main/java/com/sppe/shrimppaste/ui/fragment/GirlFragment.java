package com.sppe.shrimppaste.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.adapter.GirlItemDecoration;
import com.sppe.shrimppaste.net.GankHttpManager;
import com.sppe.shrimppaste.ui.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;

    private List<String> imageUrlList;
    private GirlAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_girl, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_girl);
        this.refreshLayout = (RefreshLayout) view.findViewById(R.id.srl_girl);

        initRecyclerView();
        initRefreshLayout();

        return view;
    }


    private void initRecyclerView() {
        imageUrlList = new ArrayList<>();
        adapter = new GirlAdapter(getContext(),imageUrlList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new GirlItemDecoration(getContext()));

        GankHttpManager.getInstance().getDataFromLocal(adapter,getContext());
        GankHttpManager.getInstance().getImages(10,getContext(),adapter);
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }
}
