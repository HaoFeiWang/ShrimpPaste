package com.sppe.shrimppaste.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sppe.shrimppaste.R;

/**
 * Created by WHF on 2017/11/5.
 */

public class GirlItemDecoration extends RecyclerView.ItemDecoration {

    private int leftMargin;
    private int rightMargin;
    private int bottomMargin;

    private Paint paint;

    public GirlItemDecoration(Context context) {
        leftMargin = context.getResources().getDimensionPixelSize(R.dimen.girl_item_left_margin);
        rightMargin = context.getResources().getDimensionPixelSize(R.dimen.girl_item_right_margin);
        bottomMargin = context.getResources().getDimensionPixelSize(R.dimen.girl_item_bottom_margin);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        int index = 0;
        //-1是Footer的原因,Footer部分不做任何绘制
        while (index < count - 1) {
            View view = parent.getChildAt(index);
            float left = view.getLeft();
            float top = view.getTop();
            float right = view.getRight();
            float bottom = view.getBottom();

            //底部紧挨Footer的Item不做下方线绘制
            if (index != count - 2 && index != count - 3) {
                //下
                c.drawRect(left - leftMargin, bottom, right + rightMargin, bottom + bottomMargin, paint);
            }
            //左
            c.drawRect(left - leftMargin, top, left, bottom, paint);
            //右
            c.drawRect(right, top, right + rightMargin, bottom, paint);
            ++index;
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(leftMargin, 0, rightMargin, bottomMargin);
    }
}
