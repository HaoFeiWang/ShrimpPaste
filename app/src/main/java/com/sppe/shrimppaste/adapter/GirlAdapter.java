package com.sppe.shrimppaste.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sppe.shrimppaste.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> imageUrlList;
    private OnItemClickListener onItemClickListener;

    private RequestOptions requestOptions;
    private RequestManager requestManager;

    public GirlAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        if (imageUrlList == null) {
            this.imageUrlList = new ArrayList<>();
        } else {
            this.imageUrlList = imageUrlList;
        }

        requestOptions = new RequestOptions()
                .placeholder(R.drawable.image_erro)
                .error(R.drawable.image_erro)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        requestManager = Glide.with(context);
    }

    @Override
    public GirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlHolder(inflater.inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(final GirlHolder holder, int position) {
        if (imageUrlList.size() > position) {
            setImage(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    private void setImage(final GirlHolder holder, final int position) {
        requestManager
                .load(imageUrlList.get(position))
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        setItemClickListener(holder, position);
                        return false;
                    }
                }).into(holder.ivContent);
    }

    private void setItemClickListener(GirlHolder holder, final int index) {
        holder.ivContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(index, v);
                }
            }
        });
    }

    public void setUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        /**
         * 点击Item
         *
         * @param position 当前item的位置
         * @param view     当前item的view
         */
        void onItemClick(int position, View view);
    }
}
