package com.will.play.pick.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickCollectionBinding
import com.will.play.pick.ui.viewmodel.PickCollectionViewModel
import com.will.play.pick.ui.viewmodel.PickGoodsDetailViewModel
import com.will.play.third.DouyinLogin

/**
 * Desc:实物领取
 *
 * Date: 2020-07-01
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/pick/collect")
class PickCollectionActivity : BaseActivity<ActivityPickCollectionBinding, PickCollectionViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_collection
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val goodId=intent.getStringExtra(ConstantConfig.RECOMMEND_ID)?:""
                //todo
                return PickCollectionViewModel(activity.application, "200030") as T
            }
        }).get(cls)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        DouyinLogin.authSuccess.observe(this, Observer {
            if (it!=null) {
                viewModel.getDouyinUserinfo(it)
            }
        })
    }

}