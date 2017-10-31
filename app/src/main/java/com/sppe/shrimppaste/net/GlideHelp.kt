package com.sppe.shrimppaste.net

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sppe.shrimppaste.R

/**
 * Created by WangHaoFei on 2017/10/24.
 */
class GlideHelp{

    private val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.image_erro)
            .error(R.drawable.image_erro)
            .skipMemoryCache(false)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    fun fillImage(context: Context,url:String,imageview: ImageView){
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageview)
    }
}
