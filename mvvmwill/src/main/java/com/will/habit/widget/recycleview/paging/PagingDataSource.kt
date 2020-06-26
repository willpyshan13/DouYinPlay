package com.will.habit.widget.recycleview.paging

import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList

/**
 * Desc: 数据源
 */
interface PagingDataSource<ITEM : ItemViewModel<*>> {

    /**
     * Desc: 数据数组
     */
    fun getItems(): DiffObservableArrayList<ITEM>

    /**
     * Desc: 加载数据
     * <p>
     *
     * @param refresh 是否是刷新
     */
    fun loadOnlineData(refresh: Boolean)

    /**
     * Desc: 是否无更多数据
     */
    fun noMore(): Boolean
}