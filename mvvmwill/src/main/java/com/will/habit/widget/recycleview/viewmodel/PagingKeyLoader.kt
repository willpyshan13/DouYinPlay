package com.will.habit.widget.recycleview.viewmodel

import com.will.habit.base.ItemViewModel
import com.will.habit.widget.recycleview.paging.LoadKeyCallback

/**
 * Desc: 使用Key加载
 * <p>
 * Date: 2020/3/26
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: linjiaqiang
 */
interface PagingKeyLoader<ITEM : ItemViewModel<*>, KEY> {

    /**
     * Desc: 加载数据，强烈推荐使用协程加载
     * <p>
     */
    fun loadData(key: KEY, loadKeyCallback: LoadKeyCallback<ITEM, KEY>)
}