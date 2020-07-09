package com.will.habit.widget.recycleview.preload

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.will.habit.widget.recycleview.FooterStatus
import com.will.habit.widget.recycleview.LoadMoreBindingRecyclerViewAdapter
import java.util.*

/**
 * Desc: 预加载处理
 */
internal class PreLoadScrollListener(private val onLoadMoreListener: OnLoadMoreListener, private val preLoadOffset: Int) : RecyclerView.OnScrollListener() {

    /**
     * 存储瀑布流item坐标
     */
    private var staggeredColumnsPositions: IntArray? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy <= 0) {
            log(String.format("onScrolled dx=%d,dy=%d", dx, dy))
            return
        }
        val adapter = recyclerView.adapter as? LoadMoreBindingRecyclerViewAdapter<*>
        if (null != adapter) {
            val dataCount = 0.coerceAtLeast(adapter.getBaseItemCount())
            log(String.format("dataCount=%d", dataCount))
            if (dataCount == 0) {
                log("dataCount is 0 ")
                return
            }
            val layoutManager = recyclerView.layoutManager
            if (null == layoutManager) {
                log("layoutManager is null")
                return
            }
            val lastVisiblePosition = getLastVisiblePosition(recyclerView)
            val visibleItemCount = layoutManager.childCount
            log(String.format("visibleItemCount=%d,lastVisiblePosition=%d", visibleItemCount, lastVisiblePosition))
            if (visibleItemCount > lastVisiblePosition) {
                log(String.format("visibleItemCount[%d] > lastVisiblePosition[%d]", visibleItemCount, lastVisiblePosition))
                return
            }
            val preLoadPosition = adapter.getBaseItemCount() - preLoadOffset
            log(String.format("visibleItemCount=%d,lastVisiblePosition=%d,mPositionOffset=%d",
                    visibleItemCount, lastVisiblePosition, preLoadOffset))
            if (lastVisiblePosition >= preLoadPosition && adapter.canLoadMore()) {
                adapter.setFooterStatus(FooterStatus.STATUS_LOADING)
                onLoadMoreListener.onLoadMore()
            }
        }
    }

    /**
     * Desc: 获取最后一个可见item的position
     * <p>
     */
    private fun getLastVisiblePosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager
        var lastVisiblePosition = 0
        try {
            if (layoutManager is LinearLayoutManager) {
                lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                log(String.format("LinearLayoutManager getLastVisiblePosition=%d", lastVisiblePosition))
            } else if (layoutManager is StaggeredGridLayoutManager) {
                val spansCount = layoutManager.spanCount
                if (spansCount == 0) {
                    return lastVisiblePosition
                }
                if (null == staggeredColumnsPositions) {
                    staggeredColumnsPositions = IntArray(spansCount)
                }
                val result = layoutManager.findLastVisibleItemPositions(staggeredColumnsPositions)
                Arrays.sort(result)
                lastVisiblePosition = result[result.size - 1]
                log(String.format("StaggeredGridLayoutManager getLastVisiblePosition=%d", lastVisiblePosition))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return lastVisiblePosition
    }

    /**
     * Desc: 日志打印
     * <p>
     */
    private fun log(log: String) {
        if (DEBUG) {
        }
    }

    companion object {
        private const val DEBUG = false
        private const val TAG = "PreLoadScrollListener"
    }
}