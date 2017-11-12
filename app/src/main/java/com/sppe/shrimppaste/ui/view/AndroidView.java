package com.sppe.shrimppaste.ui.view;

import com.sppe.shrimppaste.base.BaseMvpView;
import com.sppe.shrimppaste.data.dao.GankEntry;

import java.util.List;

/**
 * Created by WHF on 2017/11/12.
 */

public interface AndroidView extends BaseMvpView {

    void refreshData(List<GankEntry> urlList);

    void stopRefreshing();
}
