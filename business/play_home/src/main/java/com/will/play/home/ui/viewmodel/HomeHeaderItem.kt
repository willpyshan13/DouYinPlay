package com.will.play.home.ui.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.play.base.entity.BannerEntity
import com.will.play.home.R
import com.will.play.home.BR
import com.will.play.home.entity.HomeRespDataEntity
import me.tatarka.bindingcollectionadapter2.ItemBinding

class HomeHeaderItem(viewModel:FragmentHomeViewModel, private val homeData:HomeRespDataEntity?, val bannerData: BannerEntity?) :ItemViewModel<FragmentHomeViewModel>(viewModel) {
    val dataObservable = ObservableField<HomeRespDataEntity>(homeData)
    /**
     * banner列表
     */
    val bannerItemBinding = ItemBinding.of<HomeBannerItem>(BR.viewModel, R.layout.fragment_home_banner_item)
    val bannerItems = ObservableArrayList<HomeBannerItem>()

    init {
        val bannerList = bannerData?.swiperLists?.map { HomeBannerItem(viewModel,it) }.orEmpty()
        bannerItems.addAll(bannerList)
    }

    val onTalentClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            ARouter.getInstance().build("/mine/talentlist").navigation()
        }

    })

}