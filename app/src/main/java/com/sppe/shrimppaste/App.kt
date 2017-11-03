package com.sppe.shrimppaste

import android.app.Application
import com.sppe.shrimppaste.data.DataBaseHelper

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
open class App : Application(){

    override fun onCreate() {
        super.onCreate()
        DataBaseHelper.initDataBaseHelp(this)
    }
}
