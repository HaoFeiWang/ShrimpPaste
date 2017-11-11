package com.sppe.shrimppaste.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/5.
 */

public class GlideHelp {

    public static final RequestOptions options;
    public static final RequestOptions optionsNoPlace;

    static {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.image_erro)
                .error(R.drawable.image_erro)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        optionsNoPlace = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

    }


    public void fillImage(Context context, String url, ImageView imageView) {

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    public void fillImageNoPlace(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(optionsNoPlace)
                .into(imageView);
    }
}
