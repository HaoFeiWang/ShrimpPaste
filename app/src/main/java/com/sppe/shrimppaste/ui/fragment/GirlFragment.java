package com.sppe.shrimppaste.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.adapter.GirlItemDecoration;
import com.sppe.shrimppaste.base.BaseMvpFragment;
import com.sppe.shrimppaste.present.GirlPresent;
import com.sppe.shrimppaste.ui.view.GirlView;
import com.sppe.shrimppaste.ui.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlFragment extends BaseMvpFragment<GirlView, GirlPresent> implements GirlView {

    private static final String TAG = GirlFragment.class.getSimpleName();

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

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
    }

    @Override
    public GirlPresent createPresent() {
        return new GirlPresent(getContext());
    }


    private void initRecyclerView() {

        imageUrlList = new ArrayList<>();
        adapter = new GirlAdapter(getContext(), imageUrlList);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GirlItemDecoration(getContext()));

        refreshLayout.setRefreshing(true);
        present.refreshDataFromNet(1);
        present.refreshDataFromDb();
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                present.refreshDataFromNet(1);
            }
        });
    }

    @Override
    public void refreshData(List<String> urlList) {
        this.imageUrlList = urlList;
        adapter.setUrlList(urlList);
    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }
}
