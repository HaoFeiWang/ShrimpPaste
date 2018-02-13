package com.sppe.shrimppaste.net;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/5.
 */

public class GlideHelp {

    public static final RequestOptions OPTIONS;
    public static final RequestOptions OPTIONS_NO_PLACE;

    static {
        OPTIONS = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.image_erro)
                .error(R.drawable.image_erro)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        OPTIONS_NO_PLACE = new RequestOptions()
                .centerCrop()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

    }


    public void fillImage(Context context, String url, ImageView imageView) {

        Glide.with(context)
                .load(url)
                .apply(OPTIONS)
                .into(imageView);
    }

    public void fillImageNoPlace(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(OPTIONS_NO_PLACE)
                .into(imageView);
    }
}
