package com.sppe.shrimppaste.data

import android.content.Context
import android.util.Log
import com.j256.ormlite.dao.Dao
import java.sql.SQLException

/**
 * Created by @author WangHaoFei on 2017/11/3.
 */

class PhotoDao(val context: Context) {

    val dao: Dao<PhotoEntry, Int> = DataBaseHelper.getInstance(context).getDaoForClass(PhotoEntry::class.java)

    fun addPhotoEntryList(photo: PhotoEntry) {
        try {
            dao.create(photo)
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun queryPhotoEntryList(): List<PhotoEntry> {
        val list = dao.queryForAll()
        return list
    }
}
