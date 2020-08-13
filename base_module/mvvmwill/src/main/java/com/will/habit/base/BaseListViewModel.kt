package com.will.habit.base

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import com.will.habit.widget.recycleview.paging.PageIndexDataSource
import com.will.habit.widget.recycleview.viewmodel.BasePagingViewModel
import com.will.habit.widget.recycleview.viewmodel.PagingIndexLoader
import com.will.habit.widget.recycleview.paging.PagingDataSource

/**
 * Desc: 通用分页ListViewModel，使用pageIndex进行分页
 *
 */
abstract class BaseListViewModel<M : BaseModel<*>, ITEM : ItemViewModel<*>>(application: Application) :
        BasePagingViewModel<M, ITEM>(application), PagingIndexLoader<ITEM> {

    /**
     * Desc: DiffObservableArrayList 参数 DiffUtil.ItemCallback，提供数据对比
     * <p>
     * Date: 2019/12/6
     */
    abstract fun getDiffItemCallback(): DiffUtil.ItemCallback<ITEM>

    override fun createDataSource(): PagingDataSource<ITEM> {
        return PageIndexDataSource(getDiffItemCallback(), this) {
            finishLoad(it)
        }
    }

}
