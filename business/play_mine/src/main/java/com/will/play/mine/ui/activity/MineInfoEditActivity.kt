package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityInfoEditBinding
import com.will.play.mine.ui.viewmodel.MineAddressViewModel
import com.will.play.mine.ui.viewmodel.MineInfoEditViewModel

/**
 * Desc:修改我的信息
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineInfoEditActivity : BaseActivity<MineActivityInfoEditBinding, MineInfoEditViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_info_edit
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}