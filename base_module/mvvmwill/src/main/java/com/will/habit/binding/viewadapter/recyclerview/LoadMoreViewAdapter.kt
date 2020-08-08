package com.will.habit.binding.viewadapter.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.R
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.widget.recycleview.LoadMoreBindingRecyclerViewAdapter
import com.will.habit.widget.recycleview.preload.OnLoadMoreListener
import com.will.habit.widget.recycleview.preload.PreLoadScrollListener
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:
 * <p>
 *
 * @author: pengyushan
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["itemBinding", "items", "adapter", "itemIds", "loadStatus", "viewHolder", "onLoadMoreCommand"], requireAll = false)
fun <T> RecyclerView.setAdapter(itemBinding: ItemBinding<T>?,
                                items: List<T>?,
                                adapter: LoadMoreBindingRecyclerViewAdapter<T>?,
                                itemIds: BindingRecyclerViewAdapter.ItemIds<in T>?,
                                loadStatus: Int?,
                                viewHolderFactory: BindingRecyclerViewAdapter.ViewHolderFactory?,
                                onLoadMoreCommand: BindingCommand<BindingAction>?) {
    var newAdapter = adapter
    requireNotNull(itemBinding) { "itemBinding must not be null" }
    val oldAdapter = this.adapter as? LoadMoreBindingRecyclerViewAdapter<T>
    if (newAdapter == null) {
        newAdapter = oldAdapter ?: LoadMoreBindingRecyclerViewAdapter()
    }
    if (onLoadMoreCommand != null) {
        var preLoadScrollListener = this.getTag(R.id.PreLoadScrollListenerId) as? PreLoadScrollListener
        if (preLoadScrollListener == null) {
            preLoadScrollListener = PreLoadScrollListener(object : OnLoadMoreListener {
                override fun onLoadMore() {
                    onLoadMoreCommand.execute()
                }
            }, 2)
            this.addOnScrollListener(preLoadScrollListener)
            this.setTag(R.id.PreLoadScrollListenerId, preLoadScrollListener)
        }
    }
    newAdapter.itemBinding = itemBinding
    newAdapter.setItems(items)
    newAdapter.setItemIds(itemIds)
    newAdapter.setViewHolderFactory(viewHolderFactory)
    if (loadStatus != null) {
        newAdapter.setFooterStatus(loadStatus)
    }
    if (oldAdapter !== newAdapter) {
        this.adapter = newAdapter
    }
}