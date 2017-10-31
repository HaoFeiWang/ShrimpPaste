package com.sppe.shrimppaste.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.adapter.GirlAdapter
import com.sppe.shrimppaste.net.GirlRequestHelp

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_girl, container, false)
        val recyclerView = view.findViewById(R.id.rv_girl) as RecyclerView

        initAdapter(recyclerView)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        return view
    }

    private fun initAdapter(recyclerView: RecyclerView) {
        val imageUrlList = ArrayList<String>()
        val adapter = GirlAdapter(context!!, imageUrlList)
        recyclerView.adapter = adapter

        GirlRequestHelp().getImages(10, adapter)
    }

}
