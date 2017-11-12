package com.sppe.shrimppaste.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/12.
 */

public class AndroidHolder extends RecyclerView.ViewHolder{

    public TextView tvTitle;
    public TextView tvType;
    public TextView tvAuthor;
    public TextView tvDate;
    public ImageView ivContent;

    public AndroidHolder(View itemView) {
        super(itemView);

        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvType = (TextView) itemView.findViewById(R.id.tv_type);
        tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
        ivContent = (ImageView) itemView.findViewById(R.id.iv_content);
    }
}
