package com.sppe.shrimppaste.data

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * 妹子页面的实体类
 * Created by WangHaoFei on 2017/10/24.
 */
data class GirlResult(val error: Boolean, val results: List<PhotoEntry>)


@DatabaseTable(tableName = "table_photo")
data class PhotoEntry(@DatabaseField val _id: String,
                      @DatabaseField val createAt: String,
                      @DatabaseField val desc: String,
                      @DatabaseField val publishedAt: String,
                      @DatabaseField val source: String,
                      @DatabaseField val type: String,
                      @DatabaseField val url: String,
                      @DatabaseField val used: Boolean,
                      @DatabaseField val who: String)

