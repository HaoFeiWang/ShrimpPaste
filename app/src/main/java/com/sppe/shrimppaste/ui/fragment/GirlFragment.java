package com.sppe.shrimppaste.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.adapter.FooterAdapterWrapper;
import com.sppe.shrimppaste.adapter.FooterRecyclerScrollListener;
import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.adapter.GirlItemDecoration;
import com.sppe.shrimppaste.base.BaseMvpFragment;
import com.sppe.shrimppaste.present.GirlPresent;
import com.sppe.shrimppaste.ui.view.GirlView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlFragment extends BaseMvpFragment<GirlView, GirlPresent> implements GirlView {

    private static final String TAG = GirlFragment.class.getSimpleName();

    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private List<String> imageUrlList;
    private GirlAdapter adapter;
    private FooterAdapterWrapper footerAdapter;

    private int currentPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_girl, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_girl);
        this.refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_girl);

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
        footerAdapter = new FooterAdapterWrapper(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(footerAdapter);
        recyclerView.addItemDecoration(new GirlItemDecoration(getContext()));
        setScrollListener();

//        present.refreshDataFromNet(currentPage);
        present.refreshDataFromDb();
    }

    private void setScrollListener() {
        recyclerView.addOnScrollListener(new FooterRecyclerScrollListener() {
            @Override
            public void onLoadMore() {
                present.refreshDataFromNet(++currentPage);
            }
        });
    }

    private void initRefreshLayout() {
    }

    @Override
    public void refreshData(List<String> urlList) {
        this.imageUrlList = urlList;
        adapter.setUrlList(urlList);
//        adapter.notifyDataSetChanged();
        footerAdapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }
}
