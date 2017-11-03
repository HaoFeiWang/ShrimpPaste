package com.sppe.shrimppaste.data

import android.content.Context

import com.j256.ormlite.dao.Dao

import java.sql.SQLException

/**
 * Created by @author WangHaoFei on 2017/11/3.
 */

class PhotoDao(val context: Context) {

    val dao = DataBaseHelper.getInstance(context).getDaoForClass(PhotoEntry::class.java)

    fun addPhotoEntryList(photoList: Collection<PhotoEntry>) {
        try {
            dao.create(photoList)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun queryPhotoEntryList() {

    }
}
