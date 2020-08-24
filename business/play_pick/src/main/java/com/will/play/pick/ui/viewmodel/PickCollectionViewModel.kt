package com.will.play.pick.ui.viewmodel

import android.app.Application
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.BaseListViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.constant.ConstantConfig
import com.will.habit.extection.AuthException
import com.will.habit.extection.launch
import com.will.habit.utils.SPUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.recycleview.paging.LoadCallback
import com.will.play.base.web.WebViewPath
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.repository.PickRepository
import com.will.play.third.DouyinLogin
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Desc:实物领取，视频领取
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickCollectionViewModel(application: Application,val id:String) : BaseListViewModel<PickRepository, ItemViewModel<*>>(application) {
    val douyinLogin = SingleLiveEvent<Void>()
    val taobaoLogin = SingleLiveEvent<Void>()
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

    init {
        loadInit()
    }

    override fun showEmptyState() {
    }

    override fun getItemBinding(): ItemBinding<ItemViewModel<*>> {
        return ItemBinding.of { binding, _, item ->
            when (item) {
                is PickDataItem -> binding.set(BR.viewModel, R.layout.fragment_pick_item)
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
                ARouter.getInstance().build(WebViewPath.WEB_VIEW_ACTIVITY)
                        .withString(WebViewPath.URL,"http://api.tbk.dingdanxia.com/auth?state=custom_4072_${SPUtils.instance.getString(ConstantConfig.AUTHORIZATION)}&view=web")
                        .navigation()
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
            val data = model.getTaskDownload(id)
            loadCallback.onSuccess(viewModels,pageIndex,1)
            dismissDialog()
        }, {
            dismissDialog()
            if (it is AuthException){
                if(it.message!=null) {
                    ToastUtils.showShort(it.message!!)
                }
                if(it.responseCode.equals("300")) {
                    douyinLogin.call()
                }else{
                    ARouter.getInstance().build(WebViewPath.WEB_VIEW_ACTIVITY)
                            .withString(WebViewPath.URL,"http://api.tbk.dingdanxia.com/auth?state=custom_4072_${SPUtils.instance.getString(ConstantConfig.AUTHORIZATION)}&view=web")
                            .navigation()
                }
            }

        })
    }
}