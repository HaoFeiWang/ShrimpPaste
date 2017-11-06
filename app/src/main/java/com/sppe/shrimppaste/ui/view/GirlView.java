package com.sppe.shrimppaste.ui.view;

import com.sppe.shrimppaste.base.BaseMvpView;

import java.util.List;

/**
 * Created by WHF on 2017/11/6.
 */

public interface GirlView extends BaseMvpView {

    void refreshData(List<String> urlList);

    void stopRefreshing();
}
