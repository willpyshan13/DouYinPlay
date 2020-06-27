package com.will.play.mine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.will.habit.base.BaseFragment
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.FragmentMineBinding
import com.will.play.mine.ui.viewmodel.MineViewModel

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
class MineFragment : BaseFragment<FragmentMineBinding, MineViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_mine
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}