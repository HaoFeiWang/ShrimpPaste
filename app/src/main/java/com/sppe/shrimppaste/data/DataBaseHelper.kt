package com.sppe.shrimppaste.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

import java.sql.SQLException
import java.util.HashMap

/**
 * Created by @author WangHaoFei on 2017/11/3.
 */

class DataBaseHelper private constructor(context: Context) :
        OrmLiteSqliteOpenHelper(context, DATA_NAME, null, DATA_VERSION) {

    private val daoMap = HashMap<String, Dao<*,Int>>()

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable(connectionSource, PhotoEntry::class.java)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource,
                           oldVersion: Int, newVersion: Int) {
        try {
            TableUtils.dropTable<PhotoEntry, Any>(connectionSource, PhotoEntry::class.java, true)
            onCreate(database, connectionSource)
        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    @Synchronized
    fun <T> getDaoForClass(clazz: Class<T>): Dao<T, Int> {
        var dao: Dao<T, Int>? = null
        val className = clazz.simpleName
        if (daoMap.containsKey(className)) {
            dao = daoMap[className] as Dao<T, Int>
        }
        if (dao == null) {
            dao = super.getDao(clazz)
            daoMap.put(className, dao)
        }
        return dao!!
    }

    override fun close() {
        super.close()
        for (key in daoMap.keys) {
            var dao: Dao<*, *>? = daoMap[key]
            dao = null
        }
    }

    companion object {

        private val DATA_NAME = "data_http_request"
        private val DATA_VERSION = 1

        private var instance: DataBaseHelper? = null

        fun getInstance(context: Context): DataBaseHelper {
            if (instance == null) {
                synchronized(DataBaseHelper::class.java) {
                    if (instance == null) {
                        instance = DataBaseHelper(context)
                    }
                }
            }
            return instance!!
        }

        fun initDataBaseHelp(context: Context) {
            getInstance(context)
        }
    }
}
