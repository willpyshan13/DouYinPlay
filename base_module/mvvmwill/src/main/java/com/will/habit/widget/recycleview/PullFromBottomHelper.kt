package com.will.habit.widget.recycleview

import android.annotation.SuppressLint
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.widget.recycleview.preload.OnLoadMoreListener

/**
 * Desc: 修复一些场景下无法上拉加载的问题
 * <p>
 */
@SuppressLint("ClickableViewAccessibility")
internal class PullFromBottomHelper(private val recyclerView: RecyclerView, private val onLoadMoreListener: OnLoadMoreListener) {

    private val gestureDetector: GestureDetector
    private val touchScaleDistance = ViewConfiguration.get(recyclerView.context.applicationContext).scaledTouchSlop.toFloat()

    init {
        val listener = ListenerImpl()
        gestureDetector = GestureDetector(recyclerView.context.applicationContext, listener)
        recyclerView.setOnTouchListener(listener)
    }

    /**
     * Desc: 判断是否可以立即触发上拉加载
     * <p>
     */
    private fun invokePullFromEnd(): Boolean {
        if (recyclerView.visibility == View.VISIBLE && !canScrollDown()) {
            val adapter = recyclerView.adapter as? LoadMoreBindingRecyclerViewAdapter<*>
            if (adapter != null) {
                if (adapter.itemCount == 0 || adapter.getBaseItemCount() == 0) {
                    return false
                }
                if (!adapter.hasFooter() && adapter.canLoadMore()) {
                    adapter.setFooterStatus(FooterStatus.STATUS_LOADING)
                    onLoadMoreListener.onLoadMore()
                }
            }
        }
        return false
    }

    inner class ListenerImpl : View.OnTouchListener, GestureDetector.OnGestureListener {

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            gestureDetector.onTouchEvent(event)
            return false
        }

        override fun onDown(e: MotionEvent?): Boolean {
            return !recyclerView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL) && !canScrollUp()
        }

        override fun onShowPress(e: MotionEvent?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            val dispatchNestedPreScroll = recyclerView.dispatchNestedPreScroll(0, 1, null, null)
            if (!dispatchNestedPreScroll && distanceY > 0 && touchScaleDistance > 0 && distanceY >= touchScaleDistance) {
                invokePullFromEnd()
            }
            return !dispatchNestedPreScroll
        }

        override fun onLongPress(e: MotionEvent?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return true
        }
    }

    /**
     * Desc: 判断当前是否可以继续上滑加载列表
     */
    private fun canScrollDown(): Boolean {
        return recyclerView.canScrollVertically(1)
    }

    private fun canScrollUp(): Boolean {
        return recyclerView.canScrollVertically(-1)
    }
}