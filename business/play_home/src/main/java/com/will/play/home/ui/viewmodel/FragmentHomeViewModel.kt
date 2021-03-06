package com.will.play.home.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.extection.PermissionException
import com.will.habit.extection.launch
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.home.R
import com.will.play.home.BR
import com.will.play.home.repository.HomeRepository
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
class FragmentHomeViewModel(application: Application) :BaseListViewModel<HomeRepository, ItemViewModel<*>>(application) {

    val updateUrl = SingleLiveEvent<String>()

    val showConfirmMerchant = SingleLiveEvent<String>()

    val showChangeMerchant = SingleLiveEvent<Void>()

    val showWechatMerchant = SingleLiveEvent<Void>()

    override fun getDiffItemCallback(): DiffUtil.ItemCallback<ItemViewModel<*>> {
        return object : DiffUtil.ItemCallback<ItemViewModel<*>>() {
            override fun areItemsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                return false
            }

            override fun areContentsTheSame(oldItem: ItemViewModel<*>, newItem: ItemViewModel<*>): Boolean {
                return false
            }

        }
    }

    fun updateMerchant(id:String){
        launch({
            model.getDarenApply(id)
        },{
            if(it is PermissionException){
                showConfirmMerchant.call()
            }
        })
    }

    init {
        loadInit()
        getUpdateInfo()
    }

    private fun getUpdateInfo(){
        launch({
            val data = model.getUpdateInfo()
            if (data.update){
                updateUrl.value = data.url
            }
        })
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when(item){
                is HomeDataItem -> binding.set(BR.viewModel, R.layout.fragment_home_item)
                is HomeHeaderItem -> binding.set(BR.viewModel, R.layout.fragment_home_header)
            }
        }
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun loadData(pageIndex: Int, loadCallback: LoadCallback<ItemViewModel<*>>) {
        launch({
            val viewModels = mutableListOf<ItemViewModel<*>>()
            if (pageIndex ==1){
                showDialog()
                val data = model.getHomeData()
                val bannerData = model.getHomeBanner()
                viewModels.add(HomeHeaderItem(this,data,bannerData))
            }

            val listData = model.getHomeList(pageIndex)
            val dataList = listData.dataLists.mapIndexed { index, dataLists -> HomeDataItem(this,dataLists,index) }
            viewModels.addAll(dataList)

            dismissDialog()
            loadCallback.onSuccess(viewModels,pageIndex,1)
        },{

        })
    }
}