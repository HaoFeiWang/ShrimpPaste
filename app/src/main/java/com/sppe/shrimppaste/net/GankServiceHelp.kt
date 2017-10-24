package com.sppe.shrimppaste.net

import android.util.Log
import android.widget.TextView
import com.sppe.shrimppaste.constant.BASE_GANK_URL
import com.sppe.shrimppaste.data.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class GankServiceHelp {

    fun getImages(page: Int):ArrayList<String> {

        val urlList = ArrayList<String>()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_GANK_URL)
                .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        val gankService = retrofit.create(GankService::class.java)
        val call: Call<Photo> = gankService.getGirl(page)

        call.enqueue(object : Callback<Photo> {
            override fun onFailure(call: Call<Photo>?, t: Throwable?) {
                Log.e("===", "获取失败")
            }

            override fun onResponse(call: Call<Photo>?, response: Response<Photo>?) {
                if(response!=null){
                    for (photo in response.body()!!.results){
                        urlList.add(photo.url)
                    }
                }
            }

        })
        return urlList
    }

}

