package com.will.play.data.ui.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.entity.BannerEntity
import com.will.play.base.entity.PickDouyinEntity
import com.will.play.data.R
import com.will.play.data.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class DataHeaderItem(viewModel: DataViewModel,banner: BannerEntity?,val douyinData: PickDouyinEntity?) :ItemViewModel<DataViewModel>(viewModel) {
    val isDouyin = ObservableBoolean(false)
    val showDouyinList = ObservableBoolean(false)
    val showEmpty = ObservableBoolean(true)
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<DataBannerItem>(BR.viewModel, R.layout.fragment_data_banner_item)
    val bannerItems = ObservableArrayList<DataBannerItem>()

    /**
     * douyin列表
     */
    val douyinItemBinding = ItemBinding.of<DataDouyinItem>(BR.viewModel, R.layout.fragment_data_douyin_item)
    val douyinItems = ObservableArrayList<DataDouyinItem>()


    init {
        val bannerList = banner?.swiperLists?.map { DataBannerItem(viewModel,it) }.orEmpty()
        bannerItems.addAll(bannerList)
        if (douyinData!=null) {
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
            showDouyinList.set(false)
            showEmpty.set(true)
        }
    })

    val onDouyinClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            isDouyin.set(true)
            if (douyinData!=null){
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
            }
        }
    })

}