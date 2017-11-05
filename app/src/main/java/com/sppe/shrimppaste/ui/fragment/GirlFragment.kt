package com.sppe.shrimppaste.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.adapter.GirlAdapter
import com.sppe.shrimppaste.adapter.GirlItemDecoration
import com.sppe.shrimppaste.data.PhotoDao
import com.sppe.shrimppaste.data.PhotoEntry
import com.sppe.shrimppaste.net.GirlRequestHelp
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * 美图页面
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_girl, container, false)

        val refreshLayout = view.findViewById(R.id.srl_girl) as SwipeRefreshLayout
        val recyclerView = view.findViewById(R.id.rv_girl) as RecyclerView
        initRecyclerView(recyclerView)
        initRefreshLayout(refreshLayout)

        return view
    }


    private fun initRecyclerView(recyclerView: RecyclerView) {
        val imageUrlList = ArrayList<String>()
        val adapter = GirlAdapter(context!!, imageUrlList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(GirlItemDecoration(context))

        val observer = object : Observer<String> {
            val list = ArrayList<String>()
            override fun onNext(t: String) {
                Log.e("====",t)
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
        }.subscribeOn(
                Schedulers.newThread()
        ).flatMap<PhotoEntry> {
            Observable.fromIterable(it)
        }.map<String> {
            Log.e("====", it.url)
            it.url
        }.observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(observer)

        GirlRequestHelp().getImages(10, context)
    }

    private fun initRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            SwipeRefreshLayout.OnRefreshListener {

            }
        }
    }
}
