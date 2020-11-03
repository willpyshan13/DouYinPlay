package com.will.play.ui.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.will.habit.base.BaseViewModel
import com.will.play.home.repository.HomeRepository
import com.will.play.mine.BR
import com.will.play.mine.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

class WelcomeViewModel(application: Application) :BaseViewModel<HomeRepository>(application){
    val viewPagerObservableList = ObservableArrayList<WelcomeImageItem>()

    var viewPagerItemBinding = ItemBinding.of<WelcomeImageItem> { itemBinding, _, item ->
        when (item) {
            is WelcomeImageItem -> itemBinding.set(BR.viewModel, R.layout.activity_welcome_image_list)
        }
    }

    init {
        viewPagerObservableList.add(WelcomeImageItem(this, com.will.play.R.mipmap.base_icon_welcome_1))
        viewPagerObservableList.add(WelcomeImageItem(this, com.will.play.R.mipmap.base_icon_welcome_2))
        viewPagerObservableList.add(WelcomeImageItem(this, com.will.play.R.mipmap.base_icon_welcome_3))
    }

}