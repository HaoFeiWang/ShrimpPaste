package com.sppe.shrimppaste.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.adapter.GirlAdapter
import com.sppe.shrimppaste.adapter.GirlItemDecoration
import com.sppe.shrimppaste.net.GirlRequestHelp

/**
 * 美图页面
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_girl, container, false)

        val recyclerView = view.findViewById(R.id.rv_girl) as RecyclerView
        initRecyclerView(recyclerView)

        return view
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        val imageUrlList = ArrayList<String>()
        val adapter = GirlAdapter(context!!, imageUrlList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(GirlItemDecoration(context))
        GirlRequestHelp().getImages(10, adapter)
    }
}
