package com.will.habit.widget.recycleview.viewmodel

import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.habit.base.ItemViewModel

/**
 * Desc: 使用页码加载
 * <p>
 */
interface PagingIndexLoader<ITEM : ItemViewModel<*>> {

    /**
     * Desc: 加载数据，强烈推荐使用协程加载
     * <p>
     */
    fun loadData(pageIndex: Int, loadCallback: LoadCallback<ITEM>)
}