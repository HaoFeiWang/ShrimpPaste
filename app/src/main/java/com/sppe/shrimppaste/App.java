package com.sppe.shrimppaste;

import android.app.Application;

import com.sppe.shrimppaste.data.dao.DatabaseHelp;

/**
 * Created by WHF on 2017/11/5.
 */

public class App extends Application {

    private boolean launch = false;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelp.initDataBaseHelp(this);
    }

    public boolean isLaunch() {
        return launch;
    }

    public void setLaunch(boolean launch) {
        this.launch = launch;
    }
}
