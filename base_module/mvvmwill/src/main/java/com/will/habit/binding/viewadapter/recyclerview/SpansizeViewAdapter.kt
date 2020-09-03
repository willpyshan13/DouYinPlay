package com.will.habit.binding.viewadapter.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
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
@BindingAdapter(value = ["spanSize"], requireAll = false)
fun <T> RecyclerView.setSpanSize(
        spanSizeLookup: GridLayoutManager.SpanSizeLookup?) {
    if (spanSizeLookup!=null){
        if (layoutManager is GridLayoutManager){
            (layoutManager as GridLayoutManager).spanSizeLookup = spanSizeLookup
        }
    }
}