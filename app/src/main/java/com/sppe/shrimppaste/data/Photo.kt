package com.sppe.shrimppaste.data

/**
 * Created by WangHaoFei on 2017/10/24.
 */
class Photo(val error:Boolean,val results:List<PhotoEntry>){

    class PhotoEntry(val _id:String,
                     val createAt:String,
                     val desc: String,
                     val publishedAt:String,
                     val source:String,
                     val type:String,
                     val url:String,
                     val used: Boolean,
                     val who:String)

}

