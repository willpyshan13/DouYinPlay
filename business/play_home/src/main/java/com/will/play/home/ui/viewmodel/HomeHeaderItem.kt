package com.will.play.home.ui.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.play.home.R
import com.will.play.home.BR
import com.will.play.home.entity.HomeRespDataEntity
import me.tatarka.bindingcollectionadapter2.ItemBinding

class HomeHeaderItem(viewModel:FragmentHomeViewModel) :ItemViewModel<FragmentHomeViewModel>(viewModel) {
    val dataObservable = ObservableField<HomeRespDataEntity>()
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<HomeBannerItem>(BR.viewModel, R.layout.fragment_home_banner_item)
    val bannerItems = ObservableArrayList<HomeBannerItem>()

    init {
        bannerItems.add(HomeBannerItem(viewModel))
    }

    fun updateHeaderData(data: HomeRespDataEntity?){
        dataObservable.set(data)
    }

}