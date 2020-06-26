package com.will.habit.widget.recycleview.paging

import androidx.recyclerview.widget.DiffUtil
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList

/**
 * Desc: 保存分页信息
 * <p>
 */
internal class PageDataSource<ITEM : ItemViewModel<*>>(
        itemCallback: DiffUtil.ItemCallback<ITEM>,
        val loadResult: (error: Boolean) -> Unit) {

    /**
     * 数据源
     */
    val items = DiffObservableArrayList(itemCallback)
    /**
     * 加载回调
     */
    val loadCallback = LoadCallbackImpl()
    /**
     * 下一页页码
     */
    var nextPageIndex = PAGE_NUM_INIT
    /**
     * 总页数
     */
    var totalPages = PAGE_NUM_INIT
    /**
     * 是否无更多数据
     */
    val noMore
        get() = nextPageIndex > totalPages

    inner class LoadCallbackImpl : LoadCallback<ITEM> {

        override fun onSuccess(items: List<ITEM>, pageIndex: Int?, totalPages: Int?) {
            this@PageDataSource.nextPageIndex = if (pageIndex == null) PAGE_NUM_INIT else (pageIndex + 1)
            this@PageDataSource.totalPages = totalPages ?: PAGE_NUM_INIT
            this@PageDataSource.items.submit(items, pageIndex != null && pageIndex > PAGE_NUM_INIT)
            this@PageDataSource.loadResult(false)
        }

        override fun onFailure() {
            this@PageDataSource.loadResult(true)
        }
    }
}

