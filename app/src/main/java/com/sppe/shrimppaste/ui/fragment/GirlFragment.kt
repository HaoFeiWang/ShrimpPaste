package com.sppe.shrimppaste.ui.fragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
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
import com.sppe.shrimppaste.net.GankService
import com.sppe.shrimppaste.net.GankServiceHelp
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    lateinit var mImageList: ArrayList<String>
    lateinit var mAdapter: GirlAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_girl, container, false)
        val recyclerView = view.findViewById(R.id.rv_girl) as RecyclerView
        mImageList = getData()
        mAdapter = GirlAdapter(context!!,mImageList)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        return view
    }

    private fun getData(): ArrayList<String> {
        return GankServiceHelp().getImages(1)
    }

}
