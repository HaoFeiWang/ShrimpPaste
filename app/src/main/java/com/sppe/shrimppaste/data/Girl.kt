package com.sppe.shrimppaste.data

/**
 * 妹子页面的实体类
 * Created by WangHaoFei on 2017/10/24.
 */
data class GirlResult(val error: Boolean, val results: List<PhotoEntry>)

data class PhotoEntry(val _id: String,
                      val createAt: String,
                      val desc: String,
                      val publishedAt: String,
                      val source: String,
                      val type: String,
                      val url: String,
                      val used: Boolean,
                      val who: String)

