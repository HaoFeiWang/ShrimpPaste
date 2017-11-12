package com.sppe.shrimppaste.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlHolder extends RecyclerView.ViewHolder {

    public ImageView ivContent;

    public GirlHolder(View itemView) {
        super(itemView);
        ivContent = (ImageView) itemView.findViewById(R.id.iv_content);
    }
}
