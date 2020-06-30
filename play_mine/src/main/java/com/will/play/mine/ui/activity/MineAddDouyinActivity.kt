package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.ui.viewmodel.MineAddressViewModel

/**
 * Desc:抖音账号绑定
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineAddDouyinActivity : BaseActivity<MineActivityAddressBinding, MineAddressViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_douyin_binding
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}