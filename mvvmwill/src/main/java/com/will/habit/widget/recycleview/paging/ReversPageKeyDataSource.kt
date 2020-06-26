package com.will.habit.widget.recycleview.paging

import androidx.recyclerview.widget.DiffUtil
import com.will.habit.widget.recycleview.viewmodel.PagingKeyLoader
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.collection.DiffObservableArrayList

/**
 * Desc: 保存key分页信息，数据是翻转的
 * <p>
 */
internal class ReversPageKeyDataSource<ITEM : ItemViewModel<*>, KEY>(
        private val firstPageKey: KEY,
        itemCallback: DiffUtil.ItemCallback<ITEM>,
        private val sourceLoader: PagingKeyLoader<ITEM, KEY>,
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
     * 下一页key
     */
    private var nextPageKey: KEY? = null

    inner class LoadCallbackImpl : LoadKeyCallback<ITEM, KEY> {

        override fun onSuccess(items: List<ITEM>, currentKey: KEY, nextKey: KEY?, hasMore: Boolean) {
            this@ReversPageKeyDataSource.nextPageKey = if (hasMore) nextKey else null
            this@ReversPageKeyDataSource.source.addAll(0, items)
            this@ReversPageKeyDataSource.loadResult(false)
        }

        override fun onFailure() {
            this@ReversPageKeyDataSource.loadResult(true)
        }
    }

    override fun getItems(): DiffObservableArrayList<ITEM> {
        return source
    }

    override fun loadOnlineData(refresh: Boolean) {
        if (refresh) {
            sourceLoader.loadData(nextPageKey ?: firstPageKey, loadCallback)
        }
    }

    override fun noMore(): Boolean {
        return nextPageKey == null
    }
}

