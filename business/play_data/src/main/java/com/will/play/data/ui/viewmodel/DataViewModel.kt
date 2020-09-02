package com.will.play.data.ui.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.AuthException
import com.will.habit.extection.launch
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.SPUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.data.R
import com.will.play.data.BR
import com.will.play.data.repository.DataRepository
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:首页
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class DataViewModel(application: Application) : BaseListViewModel<DataRepository, ItemViewModel<*>>(application) {
    val go2Video = SingleLiveEvent<Void>()
    val douyinLogin = SingleLiveEvent<Void>()
    override fun getDiffItemCallback(): DiffUtil.ItemCallback<ItemViewModel<*>> {
        return object : DiffUtil.ItemCallback<ItemViewModel<*>>() {
            override fun areItemsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                if (oldItem is DataHeaderItem && newItem is DataHeaderItem){
                    return true
                }
                return false
            }

            override fun areContentsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                if (oldItem is DataHeaderItem && newItem is DataHeaderItem){
                    return true
                }
                return false
            }

        }
    }

    init {
        loadInit()
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is DataItem -> binding.set(BR.viewModel, R.layout.fragment_data_item)
                is DataHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_data_header)
            }
        }
    }

    fun getDouyinUserinfo(authCode:String){
        launch({
            val data = model.douyinAuth(authCode)
            callReload(false)
        },{
            if (it is AuthException){
                if(it.message!=null) {
                    ToastUtils.showShort(it.message!!)
                }
                val bundle = Bundle().apply {
                    putString(WebViewPath.URL,"${RetrofitClient.baseTbkUrl}${SPUtils.instance.getString(ConstantConfig.TOKEN)}&view=web")
                }
                startActivity(WebViewActivity::class.java,bundle)
            }
        })
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            showDialog()
            val viewModels = mutableListOf<ItemViewModel<*>>()
            val banner = model.getHomeBanner()
            val data = model.getTaskRecommend()
            viewModels.add(DataHeaderItem(this, banner))
            viewModels.add(DataItem(this,data))
            loadCallback.onSuccess(viewModels, pageIndex, 1)
            dismissDialog()
        }, {
            dismissDialog()
        })
    }
}