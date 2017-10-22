package com.sppe.shrimppaste.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sppe.shrimppaste.R

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_base_study, container, false)
    }

}
