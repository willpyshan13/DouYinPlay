package com.will.habit.widget.recycleview.paging

import androidx.recyclerview.widget.DiffUtil
import com.will.habit.widget.recycleview.viewmodel.PagingIndexLoader
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList

/**
 * Desc: 保存页码分页信息
 * <p>
 */
internal class PageIndexDataSource<ITEM : ItemViewModel<*>>(
        itemCallback: DiffUtil.ItemCallback<ITEM>,
        private val dataLoader: PagingIndexLoader<ITEM>,
        val loadResult: (error: Boolean) -> Unit) : PagingDataSource<ITEM> {

    /**
     * 数据源
     */
    private val source = DiffObservableArrayList(itemCallback)
    /**
     * 加载回调
     */
    private val loadCallback = LoadCallbackImpl()
    /**
     * 下一页页码
     */
    private var nextPageIndex = PAGE_NUM_INIT
    /**
     * 总页数
     */
    private var totalPages = PAGE_NUM_INIT

    override fun getItems(): DiffObservableArrayList<ITEM> {
        return source
    }

    override fun noMore(): Boolean {
        return nextPageIndex > totalPages
    }

    override fun loadOnlineData(refresh: Boolean) {
        if (refresh) {
            dataLoader.loadData(PAGE_NUM_INIT, loadCallback)
        } else {
            dataLoader.loadData(nextPageIndex.coerceAtLeast(2), loadCallback)
        }
    }

    inner class LoadCallbackImpl : LoadCallback<ITEM> {

        override fun onSuccess(items: List<ITEM>, pageIndex: Int?, totalPages: Int?) {
            this@PageIndexDataSource.nextPageIndex = if (pageIndex == null) PAGE_NUM_INIT else (pageIndex + 1)
            this@PageIndexDataSource.totalPages = totalPages ?: PAGE_NUM_INIT
            this@PageIndexDataSource.source.submit(items, pageIndex != null && pageIndex > PAGE_NUM_INIT)
            this@PageIndexDataSource.loadResult(false)
        }

        override fun onFailure() {
            this@PageIndexDataSource.loadResult(true)
        }
    }
}

