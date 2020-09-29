package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityWechatAuthBinding
import com.will.play.mine.databinding.MineActivityWithDrawBinding
import com.will.play.mine.ui.viewmodel.*

/**
 * Desc:提现
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineWithDrawActivity : BaseActivity<MineActivityWithDrawBinding, MineWithDrawModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_with_draw
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}