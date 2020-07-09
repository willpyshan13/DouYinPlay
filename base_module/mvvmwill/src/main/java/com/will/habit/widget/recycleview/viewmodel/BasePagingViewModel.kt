package com.will.habit.widget.recycleview.viewmodel

import android.app.Application
import androidx.databinding.ObservableInt
import com.will.habit.widget.recycleview.FooterStatus
import com.will.habit.base.BaseModel
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.widget.recycleview.RefreshStatus
import com.will.habit.widget.recycleview.paging.DBLoadCallback
import com.will.habit.widget.recycleview.paging.PagingDataSource

/**
 * Desc: 通用分页ViewModel
 * <p>
 */
abstract class BasePagingViewModel<M : BaseModel<*>, ITEM : ItemViewModel<*>>(application: Application) : BaseViewModel<M>(application), IListViewModel<ITEM> {

    private var isLoadInit = false
    /**
     * 分页信息
     */
    private val pageDataSource by lazy {
        createDataSource()
    }

    /**
     * 加载更多状态
     */
    val loadStatus = ObservableInt(FooterStatus.STATUS_NONE)
    /**
     * 刷新状态
     */
    val refreshStatus = ObservableInt(RefreshStatus.STATUS_NONE)
    /**
     * Items数据
     */
    val items = pageDataSource.getItems()

    /**
     * 下拉刷新
     */
    val onRefreshCommand = BindingCommand<BindingAction>(object : BindingAction {
        override fun call() {
            setLoadMoreStatus(FooterStatus.STATUS_NONE)
            pageDataSource.loadOnlineData(true)
        }
    })

    /**
     * 上拉加载
     */
    val onLoadMoreCommand = BindingCommand<BindingAction>(object : BindingAction {
        override fun call() {
            pageDataSource.loadOnlineData(isEmptyData())
        }
    })

    /**
     * Desc: 初始加载，由调用者自己决定调用时机
     * <p>
     */
    fun loadInit() {
        if (isLoadInit) {
            callReload(false)
            return
        }
        isLoadInit = true
        loadDB(object : DBLoadCallback<ITEM> {
            override fun onSuccess(items: List<ITEM>?) {
                if (!items.isNullOrEmpty()) {
                    this@BasePagingViewModel.items.submit(items, false)
                }
            }
        })
        pageDataSource.loadOnlineData(true)
    }

    /**
     * Desc: 重新加载数据，此方法仅供特殊情况下调用，不会加载数据库，不会显示缺省页的加载状态，仅加载数据
     * <p>
     * @param showDialog 是否显示加载中弹窗
     */
    fun callReload(showDialog: Boolean = true) {
        if (showDialog) {
            showDialog()
        }
        setLoadMoreStatus(FooterStatus.STATUS_NONE)
        pageDataSource.loadOnlineData(true)
    }

    /**
     * Desc: 结束加载时的状态处理
     * <p>
     */
    fun finishLoad(error: Boolean) {
        dismissDialog()
        setLoadMoreStatus(if (pageDataSource.noMore() && !isEmptyData()) FooterStatus.STATUS_NO_MORE else FooterStatus.STATUS_NONE)
        if (enableRefresh()) {
            refreshStatus.set(RefreshStatus.STATUS_NONE)
        }
        if (isEmptyData()) {
            if (error) {
            } else {
                showEmptyState()
            }
        } else {
        }
    }

    /**
     * Desc: 设置上拉状态
     * <p>
     */
    private fun setLoadMoreStatus(@FooterStatus status: Int) {
        if (enableLoadMore()) {
            loadStatus.set(status)
        }
    }

    /**
     * Desc: 判断是否是空数据的条件，可重写
     * <p>
     */
    protected open fun isEmptyData() = items.isEmpty()

    /**
     * Desc: 是否支持下拉刷新，默认开启
     * <p>
     */
    open fun enableRefresh() = true

    /**
     * Desc: 无更多数据时，底部是否显示无更多数据，默认显示
     * <p>
     */
    open fun enableShowNoMore() = true

    /**
     * Desc: 是否支持上拉加载，默认开启
     * <p>
     */
    open fun enableLoadMore() = true

    /**
     * Desc: 调用mStateModel显示空数据视图
     * <p>
     */
    abstract fun showEmptyState()

    /**
     * Desc: 创建数据源
     * <p>
     */
    abstract fun createDataSource(): PagingDataSource<ITEM>

}