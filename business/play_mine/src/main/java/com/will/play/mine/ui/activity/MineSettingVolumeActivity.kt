package com.will.play.mine.ui.activity

import android.os.Bundle
import com.will.habit.base.BaseActivity
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivitySettingVolumeBinding
import com.will.play.mine.ui.viewmodel.MineSettingVolumeViewModel

/**
 * Desc:声音设置
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineSettingVolumeActivity : BaseActivity<MineActivitySettingVolumeBinding, MineSettingVolumeViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_setting_volume
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}