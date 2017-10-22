package com.sppe.shrimppaste.net

import com.sppe.shrimppaste.constant.BASE_GANK_URL
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GankServiceImpl{

    var retrofit = Retrofit.Builder()
            .baseUrl(BASE_GANK_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

}

