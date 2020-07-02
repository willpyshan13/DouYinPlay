package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityVipDetailBinding
import com.will.play.mine.ui.viewmodel.MineVipDetailViewModel

/**
 * Desc:vip详情
 *
 * Date: 2020-07-02
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineVipDetailActivity : BaseActivity<MineActivityVipDetailBinding, MineVipDetailViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_vip_detail
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}