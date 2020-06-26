package com.will.habit.binding.viewadapter.recyclerview

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.will.habit.widget.recycleview.FooterStatus
import com.will.habit.R
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.widget.recycleview.LoadMoreBindingRecyclerViewAdapter
import com.will.habit.widget.recycleview.RefreshStatus
import com.will.habit.widget.recycleview.paging.PAGE_PRELOAD_OFFSET

/**
 * Desc: 上拉加载监听
 * <p>
 *
 * @param preLoadOffset 离底部还有preLoadOffset时开始加载下一页，默认2
 * @param enableRefresh 是否支持下拉刷新
 * @param enableLoadMore 是否支持上拉加载
 */
@BindingAdapter(value = ["onLoadMoreCommand", "preLoadOffset", "enableRefresh", "enableLoadMore", "enableShowNoMore", "loadStatusAttrChanged"], requireAll = false)
fun SmartRefreshLayout.onLoadMoreCommand(onLoadMoreCommand: BindingCommand<BindingAction>?,
                                         preLoadOffset: Int?,
                                         enableRefresh: Boolean?,
                                         enableLoadMore: Boolean?,
                                         enableShowNoMore: Boolean?,
                                         bindingListener: InverseBindingListener?) {
    val recyclerView = getRecyclerViewView()
    if (recyclerView != null && bindingListener != null) {
        val tag = getTag(R.id.RefreshControlId)
        if (tag !is RefreshControl) {
            val refreshControl = RefreshControl(this,
                    recyclerView,
                    preLoadOffset ?: PAGE_PRELOAD_OFFSET,
                    enableRefresh ?: true,
                    enableLoadMore ?: true,
                    enableShowNoMore ?: true) {
                bindingListener.onChange()
                onLoadMoreCommand?.execute()
            }
            setTag(R.id.RefreshControlId, refreshControl)
        }
    } else {
        if (enableRefresh != null) {
            setEnableRefresh(enableRefresh)
        }
        if (enableLoadMore != null) {
            setEnableLoadMore(enableLoadMore)
        }
        if (onLoadMoreCommand != null) {
            setOnLoadMoreListener { onLoadMoreCommand.execute() }
        }
    }
}

/**
 * Desc: 刷新状态双向绑定
 */
@BindingAdapter(value = ["onRefreshCommand", "refreshStatusAttrChanged"], requireAll = false)
fun SmartRefreshLayout.onRefreshCommand(onRefreshCommand: BindingCommand<BindingAction>?, bindingListener: InverseBindingListener?) {
    setOnRefreshListener {
        // 下拉刷新时，禁止上拉加载
        getRefreshControl()?.checkEnableLoadMore(false)
        onRefreshCommand?.execute()
        bindingListener?.onChange()
    }
}

/**
 * Desc: 设置刷新状态
 */
@BindingAdapter("refreshStatus")
fun SmartRefreshLayout.setRefreshStatus(@RefreshStatus refreshStatus: Int) {
    getRefreshControl()?.setRefreshStatus(refreshStatus)
}

/**
 * Desc: 获取刷新状态
 */
@InverseBindingAdapter(attribute = "refreshStatus", event = "refreshStatusAttrChanged")
fun SmartRefreshLayout.getRefreshStatus(): Int {
    return if (state == RefreshState.Refreshing) RefreshStatus.STATUS_REFRESHING else RefreshStatus.STATUS_NONE
}

/**
 * Desc: 设置加载更多状态，只支持RecyclerViewView的情况
 */
@BindingAdapter("loadStatus")
fun SmartRefreshLayout.setLoadStatus(@FooterStatus loadStatus: Int) {
    getRefreshControl()?.setLoadStatus(loadStatus)
}

/**
 * Desc: 加载状态双向绑定
 */
@InverseBindingAdapter(attribute = "loadStatus", event = "loadStatusAttrChanged")
fun SmartRefreshLayout.getLoadStatus(): Int {
    val recyclerViewView = getRecyclerViewView()
    if (recyclerViewView != null) {
        val adapter = recyclerViewView.adapter as? LoadMoreBindingRecyclerViewAdapter<*>
        val footerStatus = adapter?.getFooterStatus()
        return footerStatus ?: FooterStatus.STATUS_NONE
    }
    return FooterStatus.STATUS_NONE
}

/**
 * Desc: 获取RecyclerViewView
 */
private fun SmartRefreshLayout.getRecyclerViewView(): RecyclerView? {
    val childCount = this.childCount
    for (index in 0 until childCount) {
        val child = getChildAt(index)
        if (child is RecyclerView) {
            return child
        }
    }
    return null
}

private fun SmartRefreshLayout.getRefreshControl(): RefreshControl? {
    return getTag(R.id.RefreshControlId) as? RefreshControl
}