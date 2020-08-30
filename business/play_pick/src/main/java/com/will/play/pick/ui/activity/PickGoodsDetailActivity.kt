package com.will.play.pick.ui.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.ToastUtils
import com.will.play.pick.BR
import com.will.play.pick.R
import com.will.play.pick.databinding.ActivityPickGoodsDetailBinding
import com.will.play.pick.ui.dialog.PickShareDouyinFragment
import com.will.play.pick.ui.dialog.PickVipDialogFragment
import com.will.play.pick.ui.viewmodel.PickGoodsDetailViewModel
import com.will.play.third.DouyinLogin

/**
 * Desc:商品详情页
 *
 * Date: 2020-07-05
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/pick/videodetail")
class PickGoodsDetailActivity : BaseActivity<ActivityPickGoodsDetailBinding, PickGoodsDetailViewModel>() {


    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_pick_goods_detail
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.uiChange.vipDialog.observe(this, Observer {
            showVipDialog()
        })

        viewModel.uiChange.copyEvent.observe(this, Observer {
            ToastUtils.showShort("复制成功")
            val cm = application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("Label", it))
        })

        viewModel.uiChange.showShareDialog.observe(this, Observer {
            if (it!=null) {
                PickShareDouyinFragment(it).show(supportFragmentManager, "share")
            }
        })

        viewModel.uiChange.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        DouyinLogin.authSuccess.observe(this, Observer {
            if (it!=null) {
                viewModel.getDouyinUserinfo(it)
            }
        })

    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val goodId = intent.getStringExtra(ConstantConfig.GOOD_DETAIL_ID) ?: ""
                return PickGoodsDetailViewModel(activity.application, goodId) as T
            }
        }).get(cls)
    }


    private fun showVipDialog() {
        PickVipDialogFragment().show(supportFragmentManager, "")
    }


}