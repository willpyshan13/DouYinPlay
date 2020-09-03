package com.will.play.pick.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.will.habit.base.BaseFragment
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.FragmentPickBinding
import com.will.play.pick.ui.viewmodel.PickViewModel

/**
 * Desc:home
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickFragment : BaseFragment<FragmentPickBinding, PickViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_pick
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
    }
}