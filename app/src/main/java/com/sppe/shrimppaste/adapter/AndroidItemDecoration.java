package com.sppe.shrimppaste.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/12.
 */

public class AndroidItemDecoration extends RecyclerView.ItemDecoration {

    private int divideHeight;
    private int divideColor;
    private Paint paint;

    public AndroidItemDecoration(Context context) {
        this.divideHeight = context.getResources().getDimensionPixelSize(R.dimen.divide_height);
        this.divideColor = context.getResources().getColor(R.color.divide);

        this.paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(divideColor);
        paint.setStrokeWidth(divideHeight);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int count = parent.getChildCount();
        Log.e("=====", "count = " + count);
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            c.drawLine(view.getLeft(), view.getBottom(), view.getRight(), view.getBottom(), paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divideHeight;
    }
}
