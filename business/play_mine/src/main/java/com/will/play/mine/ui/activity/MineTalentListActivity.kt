package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.play.base.constant.Constants
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityTalentListBinding
import com.will.play.mine.ui.viewmodel.MineSaleRecordViewModel
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
@Route(path = "/mine/talentlist")
class MineTalentListActivity : BaseActivity<MineActivityTalentListBinding, MineTalentListViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_talent_list
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        viewModel.talentName.set(intent.extras?.getString("name"))
        bindingToolBar?.ivRightIcon?.setImageResource(R.mipmap.base_mine_add_douyin)
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val talentId=intent.getIntExtra(Constants.COMMON_ID,0)
                val name = intent.extras?.getString("name")
                if (name!=null) {
                    return if (name.contains("达人")) {
                        MineTalentListViewModel(activity.application, null,talentId) as T
                    } else {
                        MineTalentListViewModel(activity.application, talentId,null) as T
                    }
                }
                return MineTalentListViewModel(activity.application, talentId,null) as T
            }
        }).get(cls)
    }

}