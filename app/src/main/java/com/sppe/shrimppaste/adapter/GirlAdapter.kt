package com.sppe.shrimppaste.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.net.GlideHelp

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GirlAdapter(val context: Context,var imageList: ArrayList<String>): RecyclerView.Adapter<GirlHolder>(){

    val inflater: LayoutInflater = LayoutInflater.from(context)
    val glideHelp = GlideHelp()

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GirlHolder {
        return GirlHolder(inflater.inflate(R.layout.item_girl,parent,false))
    }

    override fun onBindViewHolder(holder: GirlHolder?, position: Int) {
        glideHelp.fillImage(context,imageList[position],holder!!.ivContent)
    }

}
