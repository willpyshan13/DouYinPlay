package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityTalentBinding
import com.will.play.mine.ui.viewmodel.MineTalentViewModel

/**
 * Desc:达人信息
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineTalentInfoActivity : BaseActivity<MineActivityTalentBinding, MineTalentViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_talent
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

}