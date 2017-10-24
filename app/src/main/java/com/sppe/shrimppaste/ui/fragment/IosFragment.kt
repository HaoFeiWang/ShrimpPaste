package com.sppe.shrimppaste.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.net.GankServiceHelp

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class IosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_ios, container, false)
        view.findViewById(R.id.btn_request).setOnClickListener { GankServiceHelp().getImages(10) }
        return view
    }




}
