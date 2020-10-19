package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityTalentListBinding
import com.will.play.mine.ui.viewmodel.MineTalentListViewModel

/**
 * Desc:达人库
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineTalentListActivity : BaseActivity<MineActivityTalentListBinding, MineTalentListViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_talent_list
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        bindingToolBar?.ivRightIcon?.setImageResource(R.mipmap.base_mine_add_douyin)
    }
}