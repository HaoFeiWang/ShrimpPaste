package com.sppe.shrimppaste.net

import com.sppe.shrimppaste.adapter.GirlAdapter
import com.sppe.shrimppaste.constant.BASE_GANK_URL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlRequestHelp {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_GANK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val gankService = retrofit.create(GankService::class.java)

    fun getImages(page: Int, adapter: GirlAdapter) {
        gankService.getGirl(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { Observable.fromIterable(it.results) }
                .map { it.url }
                .subscribe { adapter.addImageUrl(it) }
    }

}


