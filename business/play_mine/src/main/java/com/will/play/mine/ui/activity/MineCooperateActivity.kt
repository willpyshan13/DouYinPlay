package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityCooperateBinding
import com.will.play.mine.ui.viewmodel.MineCooperateViewModel
import com.will.play.mine.ui.viewmodel.MineWalletViewModel

/**
 * Desc:我的合作
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineCooperateActivity : BaseActivity<MineActivityCooperateBinding, MineCooperateViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_cooperate
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}