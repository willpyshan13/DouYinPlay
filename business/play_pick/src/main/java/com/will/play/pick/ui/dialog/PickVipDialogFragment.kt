package com.will.play.pick.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.will.habit.base.BaseDialogFragment
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.DialogPickVipLayoutBinding
import com.will.play.pick.ui.viewmodel.PickVipDialogViewModel

/**
 * Desc:商品详情页VIP对话框
 *
 * Date: 2020-07-12 11:55
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
class PickVipDialogFragment :BaseDialogFragment<DialogPickVipLayoutBinding, PickVipDialogViewModel>(){
    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return  R.layout.dialog_pick_vip_layout
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }


}