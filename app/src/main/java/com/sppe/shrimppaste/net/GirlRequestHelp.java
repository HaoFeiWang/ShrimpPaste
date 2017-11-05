package com.sppe.shrimppaste.net;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlRequestHelp {

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
