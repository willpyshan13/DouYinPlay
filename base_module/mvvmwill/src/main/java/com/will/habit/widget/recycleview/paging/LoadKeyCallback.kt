package com.will.habit.widget.recycleview.paging

/**
 * Desc: 使用Key进行分页的加载回调
 * <p>
 */
interface LoadKeyCallback<ITEM, KEY> {

    /**
     * Desc: 加载成功
     * <p>
     *
     * @param items 当次加载的items
     * @param currentKey 当前页的key，为空表示当前为刷新
     * @param nextKey 下一页的key，为空表示没有更多数据了
     * @param hasMore 辅助字段，表示没有更多数据。不传的话使用nextKey判断是否有更多数据
     */
    fun onSuccess(items: List<ITEM>, currentKey: KEY, nextKey: KEY?, hasMore: Boolean = nextKey != null)

    /**
     * Desc: 加载失败
     * <p>
     */
    fun onFailure()
}