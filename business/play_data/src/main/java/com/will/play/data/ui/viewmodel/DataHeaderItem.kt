package com.will.play.data.ui.viewmodel

import android.os.Bundle
import android.text.format.DateUtils
import androidx.core.util.TimeUtils
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.SPUtils
import com.will.play.base.entity.BannerEntity
import com.will.play.base.entity.PickDouyinEntity
import com.will.play.base.entity.PickTaobaoEntity
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.data.R
import com.will.play.data.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.text.SimpleDateFormat

class DataHeaderItem(viewModel: DataViewModel,banner: BannerEntity?,val douyinData: PickDouyinEntity?,val taobaoData: PickTaobaoEntity?) :ItemViewModel<DataViewModel>(viewModel) {
    val isDouyin = ObservableBoolean(false)
    val showDouyinList = ObservableBoolean(false)
    val showTaobaoList = ObservableBoolean(false)
    val showEmpty = ObservableBoolean(true)
    val currentDate = ObservableField(SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()))
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<DataBannerItem>(BR.viewModel, R.layout.fragment_data_banner_item)
    val bannerItems = ObservableArrayList<DataBannerItem>()

    /**
     * douyin列表
     */
    val douyinItemBinding = ItemBinding.of<Any> { itemBinding, _, item ->
        when (item) {
            is DataDouyinItem -> itemBinding.set(BR.viewModel, R.layout.fragment_data_douyin_item)
            is DataTaobaoItem -> itemBinding.set(BR.viewModel, R.layout.fragment_data_taobao_item)
        }
    }

    val douyinItems = ObservableArrayList<ItemViewModel<*>>()

    init {
        val bannerList = banner?.swiperLists?.map { DataBannerItem(viewModel,it) }.orEmpty()
        bannerItems.addAll(bannerList)

        if (taobaoData!=null){
            val taobaoList = taobaoData.dataLists.map { DataTaobaoItem(viewModel,it) }
            douyinItems.addAll(taobaoList)
            showDouyinList.set(true)
            showTaobaoList.set(true)
            showEmpty.set(false)
        }else if (douyinData!=null) {
            val douyinList = douyinData.douyinVideoLists.map { DataDouyinItem(viewModel, it) }
            douyinItems.addAll(douyinList)
        }
    }

    fun updateDouyinData(douyin: PickDouyinEntity){
        val douyinList = douyin.douyinVideoLists.map { DataDouyinItem(viewModel, it) }
        douyinItems.addAll(douyinList)
    }

    val onTaobaoClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            isDouyin.set(false)
            douyinItems.clear()
            if (taobaoData!=null){
                val taobaoList = taobaoData.dataLists.map { DataTaobaoItem(viewModel,it) }
                douyinItems.addAll(taobaoList)
                showTaobaoList.set(true)
                showDouyinList.set(true)
                showEmpty.set(false)
            }else{
                showDouyinList.set(false)
                showEmpty.set(true)
            }
        }
    })

    val onDouyinClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            isDouyin.set(true)
            showTaobaoList.set(false)
            douyinItems.clear()
            if (douyinData!=null){
                val douyinList = douyinData.douyinVideoLists.map { DataDouyinItem(viewModel, it) }
                douyinItems.addAll(douyinList)
                showDouyinList.set(true)
                showEmpty.set(false)
            }else{
                showEmpty.set(true)
            }
        }
    })

    val onBindingClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            if (isDouyin.get()){
                viewModel.douyinLogin.call()
            }else{
                val bundle = Bundle().apply {
                    putString(WebViewPath.URL,"${RetrofitClient.baseTbkUrl}${SPUtils.instance.getString(ConstantConfig.TOKEN)}${RetrofitClient.baseTbkUrlView}")
                }
                viewModel.startActivity(WebViewActivity::class.java,bundle)
            }
        }
    })

}