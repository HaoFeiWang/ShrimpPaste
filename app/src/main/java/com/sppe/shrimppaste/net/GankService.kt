package com.sppe.shrimppaste.net

import android.graphics.Bitmap
import com.sppe.shrimppaste.data.Article
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
interface GankService {

    @GET("/data/福利/10/{page}")
    fun getGirl(@Path("page") page: Int): List<Bitmap>

    @GET("/data/Android/10/{page}")
    fun getAndroid(@Path("page") page: Int): List<Article>

    @GET("/data/iOS/10/{page}")
    fun getIos(@Path("page") page: Int): List<Article>

}
