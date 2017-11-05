package com.sppe.shrimppaste.net

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import com.sppe.shrimppaste.adapter.GirlAdapter
import com.sppe.shrimppaste.constant.BASE_GANK_URL
import com.sppe.shrimppaste.data.PhotoDao
import com.sppe.shrimppaste.data.PhotoEntry
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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


    fun getImages(page: Int, context: Context,adapter:GirlAdapter) {

        gankService.getGirl(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    Observable.fromIterable(it.results) }
                .subscribe {
                    Log.e("===","111")
                    Log.e("===","${it}")
                    PhotoDao(context).addPhotoEntryList(it)
                    getDataFromLocal(adapter,context)
                    Log.e("===","停止刷新")
                }
    }


    fun getDataFromLocal(adapter: GirlAdapter, context: Context) {

        val observer = object : Observer<String> {
            val list = ArrayList<String>()
            override fun onNext(t: String) {
                list.add(t)
            }

            override fun onError(e: Throwable) {
                adapter.addImageUrl(list)
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                adapter.addImageUrl(list)
            }

        }

        Observable.create<List<PhotoEntry>> {
            it.onNext(PhotoDao(context).queryPhotoEntryList())
            it.onComplete()
        }.subscribeOn(Schedulers.newThread())
                .flatMap<PhotoEntry> { Observable.fromIterable(it) }
                .map<String> { it.url }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }


}


