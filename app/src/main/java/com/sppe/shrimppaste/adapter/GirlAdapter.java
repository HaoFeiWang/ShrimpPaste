package com.sppe.shrimppaste.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.net.GlideHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlAdapter extends RecyclerView.Adapter<GirlHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> imageUrlList;
    private GlideHelp glideHelp;
    private OnItemClickListener onItemClickListener;


    public GirlAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.glideHelp = new GlideHelp();
        if (imageUrlList == null) {
            this.imageUrlList = new ArrayList<>();
        } else {
            this.imageUrlList = imageUrlList;
        }
    }

    @Override
    public GirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GirlHolder(inflater.inflate(R.layout.item_girl, parent, false));
    }

    @Override
    public void onBindViewHolder(GirlHolder holder,int position) {
        final int index = position;
        if (imageUrlList.size() > position) {
            glideHelp.fillImage(context, imageUrlList.get(position), holder.ivContent);
        }

        holder.ivContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(index);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }

    public void setUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
