package com.will.habit.widget.recycleview.paging

import com.will.habit.base.ItemViewModel

/**
 * Desc: 数据库加载回调
 * <p>
 */
interface DBLoadCallback<ITEM : ItemViewModel<*>> {

    fun onSuccess(items: List<ITEM>?)
}