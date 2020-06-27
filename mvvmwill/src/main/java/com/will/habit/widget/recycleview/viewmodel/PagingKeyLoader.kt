package com.will.habit.widget.recycleview.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.widget.recycleview.paging.LoadKeyCallback

/**
 * Desc: 使用Key加载
 * <p>
 */
interface PagingKeyLoader<ITEM : ItemViewModel<*>, KEY> {

    /**
     * Desc: 加载数据，强烈推荐使用协程加载
     * <p>
     */
    fun loadData(key: KEY, loadKeyCallback: LoadKeyCallback<ITEM, KEY>)
}