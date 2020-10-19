package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityReportBinding
import com.will.play.mine.ui.viewmodel.MineAddressViewModel

/**
 * Desc:举报
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineReportActivity : BaseActivity<MineActivityReportBinding, MineAddressViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_report
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}