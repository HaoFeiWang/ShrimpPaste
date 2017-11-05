package com.sppe.shrimppaste.data

import com.j256.ormlite.field.DatabaseField
import com.sppe.shrimppaste.annotations.Poko
import com.j256.ormlite.table.DatabaseTable

/**
 * 妹子页面的实体类
 * Created by WangHaoFei on 2017/10/24.
 */
data class GirlResult(val error: Boolean, val results: List<PhotoEntry>)

@DatabaseTable(tableName = "table_photo")
@Poko
data class PhotoEntry(@DatabaseField var _id: String,
                      @DatabaseField var createAt: String,
                      @DatabaseField var desc: String,
                      @DatabaseField var publishedAt: String,
                      @DatabaseField var source: String,
                      @DatabaseField var type: String,
                      @DatabaseField var url: String,
                      @DatabaseField var used: Boolean,
                      @DatabaseField var who: String)

