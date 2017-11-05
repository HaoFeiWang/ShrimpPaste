package com.sppe.shrimppaste.ui.view

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Scroller


/**
 * Created by WHF on 2017/11/5.
 */
class RefreshLayout(context: Context, attrs: AttributeSet) : SwipeRefreshLayout(context, attrs) {

    /**
     * 阻尼系数
     */
    val DAMP_QUOTIETY = 0.3f
    /**
     * 回弹时间
     */
    val SCROLL_DURATION = 300
    /**
     * 滚动开始
     */
    var start: Int = 0
    /**
     * 上一次滑动的坐标
     */
    var lastY: Int = 0
    /**
     * 滚动辅助类
     */
    val scroller = Scroller(context)

    /**
     * 是否拦截
     */
    var intercept: Boolean = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> start = scrollY
            MotionEvent.ACTION_MOVE -> {
                if (!scroller.isFinished) {
                    scroller.abortAnimation()  //终止动画
                }
                scrollTo(0, ((lastY - y) * DAMP_QUOTIETY).toInt())
            }
            MotionEvent.ACTION_UP -> {
                val end = scrollY
                val scrollY = end - start
                scroller.startScroll(0, end, 0, -scrollY, SCROLL_DURATION)
            }
        }
        postInvalidate()
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val y = ev.y.toInt()
        when (ev.action) {
            MotionEvent.ACTION_DOWN ->
                lastY = y
            MotionEvent.ACTION_MOVE ->
                if (y - lastY < 0 && intercept) {
                    return true
                }
        }
        return false
    }


    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.currY)
            postInvalidate()
        }
    }
}
