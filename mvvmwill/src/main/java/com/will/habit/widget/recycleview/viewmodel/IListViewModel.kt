package com.will.habit.widget.recycleview.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.ItemViewModel
import com.will.habit.widget.recycleview.paging.DBLoadCallback
import com.will.habit.widget.recycleview.paging.PAGE_PRELOAD_OFFSET
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc: ListViewModel接口
 * <p>
 */
interface IListViewModel<ITEM : ItemViewModel<*>> {

    /**
     * Desc: 加载数据库数据，如果有加载数据库的需求，实现此方法
     * <p>
     */
    fun loadDB(loadCallback: DBLoadCallback<ITEM>) {
        //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Desc: RecyclerView的ItemBinding
     * <p>
     */
    fun getItemBinding(): ItemBinding<ITEM>

    /**
     * Desc: 分割线，不需要分割线可返回null
     * <p>
     */
    fun getItemDecoration(): RecyclerView.ItemDecoration?

    /**
     * Desc: 滑动到距离底部多少个Item时开始加载下一页
     * <p>
     */
    fun getPreLoadOffset() = PAGE_PRELOAD_OFFSET
}