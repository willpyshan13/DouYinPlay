package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.SPUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.base.constant.Constants
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityTalentBinding
import com.will.play.mine.ui.viewmodel.MineTalentViewModel

/**
 * Desc:达人信息
 *
 * Date: 2020-10-19
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
@Route(path = "/mine/talentinfo")
class MineTalentInfoActivity : BaseActivity<MineActivityTalentBinding, MineTalentViewModel>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_talent
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showConfirmMerchant.observe(this,{
                showConfirm()
        })

    }

    override fun needToolBar(): Boolean {
        return false
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                val talentId=intent.getStringExtra(Constants.COMMON_ID)?:""
                //todo
                return MineTalentViewModel(activity.application, talentId) as T
            }
        }).get(cls)
    }

    private fun showConfirm(){
        ConfirmDialog(this, true)
                .setTtitle("确认身份")
                .setMessage("当前身份为非付费商家，暂不支持此权限")
                .setNegativeButtonColor(ContextCompat.getColor(this,R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(this,R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("升级商家") { p0, p1 ->
                    p0.dismiss()
                    showWechatConfirm()
                }.create().show();
    }

    private fun showMerchantConfirm(){
        ConfirmDialog(this, true)
                .setTtitle("确认身份")
                .setMessage("每个身份只有一次选择机会，请慎重考虑")
                .setNegativeButtonColor(ContextCompat.getColor(this,R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(this,R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("转换为商家") { p0, p1 ->
                    SPUtils.instance.put("first_init",false)
                }.create().show();
    }

    private fun showWechatConfirm(){
        ConfirmDialog(this, true)
                .setTtitle("添加客服转换身份")
                .setMessage("微信号码a18030144436\n复制到微信添加")
                .setNegativeButtonColor(ContextCompat.getColor(this,R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(this,R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("复制") { p0, p1 ->
                    ClipboardUtils.copyText("a18030144436")
                    ToastUtils.showLong("微信已复制到剪贴板")
                    p0.dismiss()
                }.create().show();
    }
    
}