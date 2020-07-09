package com.will.habit.binding.viewadapter.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.will.habit.widget.recycleview.FooterStatus
import com.will.habit.widget.recycleview.preload.PullFromBottomHelper
import com.will.habit.R
import com.will.habit.widget.recycleview.LoadMoreBindingRecyclerViewAdapter
import com.will.habit.widget.recycleview.RefreshStatus
import com.will.habit.widget.recycleview.preload.OnLoadMoreListener
import com.will.habit.widget.recycleview.preload.PreLoadScrollListener

/**
 * Desc: smartRefreshLayout及recyclerView 下拉刷新及上拉加载状态处理
 * <p>
 *
 * @param supportRefresh 是否支持下拉刷新
 * @param supportLoadMore 是否支持上拉加载
 * @param loadMore 上拉加载回调
 */
internal class RefreshControl(private val smartRefreshLayout: SmartRefreshLayout,
                              private val recyclerView: RecyclerView,
                              preLoadOffset: Int,
                              private val supportRefresh: Boolean,
                              private val supportLoadMore: Boolean,
                              private val enableShowNoMore: Boolean,
                              private val loadMore: () -> Unit) {

    init {
        smartRefreshLayout.setEnableRefresh(supportRefresh)
        // 禁止SmartRefreshLayout自带的上拉加载
        smartRefreshLayout.setEnableLoadMore(false)
        smartRefreshLayout.setEnableAutoLoadMore(false)
        if (supportLoadMore) {
            var preLoadScrollListener = recyclerView.getTag(R.id.PreLoadScrollListenerId) as? PreLoadScrollListener
            if (preLoadScrollListener == null) {
                val loadMoreListener = object : OnLoadMoreListener {
                    override fun onLoadMore() {
                        loadMore.invoke()
                        // 上拉加载时，禁止下拉刷新
                        smartRefreshLayout.setEnableRefresh(false)
                    }
                }
                preLoadScrollListener = PreLoadScrollListener(loadMoreListener, preLoadOffset)
                recyclerView.addOnScrollListener(preLoadScrollListener)
                recyclerView.setTag(R.id.PreLoadScrollListenerId, preLoadScrollListener)
                PullFromBottomHelper(recyclerView, loadMoreListener)
            }
        }
    }

    /**
     * Desc: 设置上拉加载状态
     * <p>
     *
     * @param loadStatus 状态
     */
    fun setLoadStatus(@FooterStatus loadStatus: Int) {
        val adapter = recyclerView.adapter as? LoadMoreBindingRecyclerViewAdapter<*>
        adapter?.setEnableShowNoMore(enableShowNoMore)
        adapter?.setFooterStatus(loadStatus)
        // 加载结束时，开启下拉刷新
        checkEnableRefresh(loadStatus != FooterStatus.STATUS_LOADING)
    }

    /**
     * Desc: 设置刷新状态
     * <p>
     * @param refreshStatus 状态
     */
    fun setRefreshStatus(@RefreshStatus refreshStatus: Int) {
        if (refreshStatus == RefreshStatus.STATUS_NONE && smartRefreshLayout.state == RefreshState.Refreshing) {
            smartRefreshLayout.finishRefresh(0)
            // 刷新结束时，开启上拉加载
            checkEnableLoadMore(true)
        } else if (refreshStatus == RefreshStatus.STATUS_FORBID) {
            smartRefreshLayout.finishRefresh(0)
            smartRefreshLayout.setEnableRefresh(false)
        }
    }

    /**
     * Desc: 判断是否可下拉刷新，优先判断是否支持下拉刷新
     * <p>
     *
     * @param enable 是否可下拉刷新
     */
    private fun checkEnableRefresh(enable: Boolean) {
        if (supportRefresh) {
            smartRefreshLayout.setEnableRefresh(enable)
        }
    }

    /**
     * Desc: 判断是否可上拉加载，优先判断是否支持上拉加载
     *
     * @param enable 是否可上拉加载
     */
    fun checkEnableLoadMore(enable: Boolean) {
        if (supportLoadMore) {
            (recyclerView.adapter as? LoadMoreBindingRecyclerViewAdapter<*>)?.setLoadMoreEnable(enable)
        }
    }
}