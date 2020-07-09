package com.will.habit.widget.recycleview.paging

/**
 * Desc: 加载回调
 * <p>
 */
interface LoadCallback<ITEM> {

    /**
     * Desc: 加载成功
     * <p>
     *
     * @param items 当次加载的items
     * @param pageIndex 当次加载的页码，一般由服务端返回，也可自己定义
     * @param totalPages 总页数，一般由服务端返回
     */
    fun onSuccess(items: List<ITEM>, pageIndex: Int?, totalPages: Int?)

    /**
     * Desc: 加载失败
     * <p>
     */
    fun onFailure()
}