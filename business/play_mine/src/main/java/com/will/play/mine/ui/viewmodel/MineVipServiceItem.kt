package com.will.play.mine.ui.viewmodel

import androidx.core.content.ContextCompat
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel
import com.will.habit.utils.Utils

/**
 * 我的会员服务item
 */
class MineVipServiceItem(viewModel: BaseViewModel<*>, resIcon:Int, val title:String) :ItemViewModel<BaseViewModel<*>>(viewModel) {

    val resDrawable = ContextCompat.getDrawable(Utils.getContext(),resIcon)

}