package com.sppe.shrimppaste.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sppe.shrimppaste.R

/**
 *
 * Created by @author WangHaoFei on 2017/11/2.
 */
class GirlItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private val leftMargin = context.resources.getDimensionPixelSize(R.dimen.girl_item_left_margin)
    private val rightMargin = context.resources.getDimensionPixelSize(R.dimen.girl_item_right_margin)
    private val bottomMargin = context.resources.getDimensionPixelSize(R.dimen.girl_item_bottom_margin)

    private val paint = Paint()

    init {
        paint.color = context.resources.getColor(R.color.divide)
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val count = parent!!.childCount
        var index = 0
        while (index < count) {
            val view = parent.getChildAt(index)
            val left = view.left.toFloat()
            val top = view.top.toFloat()
            val right = view.right.toFloat()
            val bottom = view.bottom.toFloat()

            c!!.drawRect(left - leftMargin, top, left, bottom, paint)
            c.drawRect(right, top, right + rightMargin, bottom, paint)
            c.drawRect(left - leftMargin, bottom, right + rightMargin, bottom + bottomMargin, paint)

            ++index
        }

    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect!!.set(leftMargin, 0, rightMargin, bottomMargin)
    }
}