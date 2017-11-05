package com.sppe.shrimppaste.ui.fragment

import android.content.Context
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

/**
 * 美图页面
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    val girlRequestHelp = GirlRequestHelp()
    val imageUrlList = ArrayList<String>()
    var adapter: GirlAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_girl, container, false)

        val recyclerView = view.findViewById(R.id.rv_girl) as RecyclerView
        val refreshLayout = view.findViewById(R.id.srl_girl) as SwipeRefreshLayout

        initRecyclerView(recyclerView)
        initRefreshLayout(refreshLayout)
        refreshLayout.isRefreshing = true

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        adapter = GirlAdapter(context!!, imageUrlList)
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        Log.e("===", "$context")
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(GirlItemDecoration(context))

        girlRequestHelp.getDataFromLocal(adapter!!, context)
    }

    private fun initRefreshLayout(refreshLayout: SwipeRefreshLayout) {
        refreshLayout.setOnRefreshListener {
            girlRequestHelp.getImages(10, context, adapter!!)
        }
    }
}
