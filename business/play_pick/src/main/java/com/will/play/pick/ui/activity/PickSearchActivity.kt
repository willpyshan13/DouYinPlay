package com.will.play.pick.ui.activity

import android.os.Bundle
import com.will.play.pick.R
import com.will.play.pick.BR
import com.will.play.pick.ui.viewmodel.PickSearchViewModel
import com.will.habit.base.BaseActivity
import com.will.play.pick.databinding.ActivityPickSearchBinding

/**
 * Desc:搜索页面
 *
 * Date: 2020-07-12
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @author: zhuanghongzhan
 */
class PickSearchActivity : BaseActivity<ActivityPickSearchBinding, PickSearchViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?) = R.layout.activity_pick_search

    override fun initVariableId() = BR.viewModel


    override fun needToolBar(): Boolean = false


}
