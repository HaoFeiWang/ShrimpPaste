package com.sppe.shrimppaste.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.ui.FooterHelp;
import com.sppe.shrimppaste.adapter.GirlAdapter;
import com.sppe.shrimppaste.adapter.GirlItemDecoration;
import com.sppe.shrimppaste.base.BaseMvpFragment;
import com.sppe.shrimppaste.data.contacts.Contacts;
import com.sppe.shrimppaste.present.GirlPresent;
import com.sppe.shrimppaste.ui.activity.PhotoActivity;
import com.sppe.shrimppaste.ui.view.GirlView;
import com.sppe.shrimppaste.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 美女页
 * Created by @author WHF on 2017/11/5.
 */

public class GirlFragment extends BaseMvpFragment<GirlView, GirlPresent> implements GirlView {

    private static final String TAG = Contacts.LOG_TAG + GirlFragment.class.getSimpleName();

    private static final int START_PAGE = 1;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private RelativeLayout rlFooter;

    private int currentPage;
    private List<String> imageUrlList;

    private GirlAdapter adapter;
    private FooterHelp footerHelp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, container, false);

        this.recyclerView = (RecyclerView) view.findViewById(R.id.rv_girl);
        this.refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_girl);
        rlFooter = (RelativeLayout) view.findViewById(R.id.rl_footer);

        currentPage = START_PAGE;

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initRefreshLayout();
        initData();
    }

    @Override
    public GirlPresent createPresent() {
        return new GirlPresent(getContext());
    }


    private void initRecyclerView() {

        imageUrlList = new ArrayList<>();
        adapter = new GirlAdapter(getContext(), imageUrlList);
        adapter = new GirlAdapter(getContext(), imageUrlList);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GirlItemDecoration(getContext()));

        setOnItemClickListener(adapter);
        setScrollToFooterListener();
    }

    private void initRefreshLayout() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                present.refreshDataFromNet(START_PAGE);
            }
        });
    }

    private void initData() {
        present.refreshDataFromDb();
        SharedPreferencesUtil.getString(getContext(),);
    }


    private void setScrollToFooterListener() {
        footerHelp = new FooterHelp();
        footerHelp.attachScrollToFooterListener(recyclerView, new FooterHelp.ScrollToFooterListener() {
            @Override
            public void scrollToFooter() {
                rlFooter.setVisibility(View.VISIBLE);
                present.refreshDataFromNet(++currentPage);
            }
        });
    }

    private void setOnItemClickListener(GirlAdapter girlAdapter) {
        girlAdapter.setOnItemClickListener(new GirlAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String imageUrl = imageUrlList.get(position);

                Intent intent = new Intent(getContext(), PhotoActivity.class);
                intent.putExtra(Contacts.BUNDLE_RUL, imageUrl);

                startActivity(intent);
            }
        });
    }

    @Override
    public void refreshDataSuccess(List<String> urlList) {
        if (urlList == null || urlList.size() == 0) {
            return;
        }

        imageUrlList = urlList;
        adapter.setUrlList(urlList);
        adapter.notifyDataSetChanged();

        footerHelp.resetScrollToFooterTag();
        rlFooter.setVisibility(View.GONE);
    }

    @Override
    public void refreshDataError() {

        footerHelp.resetScrollToFooterTag();
        rlFooter.setVisibility(View.GONE);
    }
}
