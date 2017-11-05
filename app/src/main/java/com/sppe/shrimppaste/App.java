package com.sppe.shrimppaste;

import android.app.Application;

/**
 * Created by WHF on 2017/11/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseHelper.initDataBaseHelp(this);
    }
}
