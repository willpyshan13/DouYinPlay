package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.ui.viewmodel.MineDouyinAddViewModel

/**
 * Desc:抖音账号绑定
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineAddDouyinActivity : BaseActivity<MineActivityAddressBinding, MineDouyinAddViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_douyin_add
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        bindingToolBar?.ivRightIcon?.setImageResource(R.mipmap.base_mine_add_douyin)
    }
}