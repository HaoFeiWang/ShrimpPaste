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
import kotlinx.android.synthetic.main.fragment_girl.*

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlFragment : Fragment() {

    lateinit var mImageList: ArrayList<Bitmap>
    lateinit var mAdapter: GirlAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        mImageList = getData()
        mAdapter = GirlAdapter(context!!,mImageList)
        rv_girl.adapter = mAdapter
        rv_girl.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_girl, container, false)
    }

    private fun getData(): ArrayList<Bitmap> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
