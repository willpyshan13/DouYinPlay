package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityVipDetailBinding
import com.will.play.mine.databinding.MineActivityVipPayBinding
import com.will.play.mine.ui.viewmodel.MineVipDetailViewModel
import com.will.play.mine.ui.viewmodel.MineVipPayViewModel

/**
 * Desc:vip详情
 *
 * Date: 2020-07-02
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineVipPayActivity : BaseActivity<MineActivityVipPayBinding, MineVipPayViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_vip_pay
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}