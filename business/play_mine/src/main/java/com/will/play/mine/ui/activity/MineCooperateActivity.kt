package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.will.habit.base.BaseActivity
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.SPUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityCooperateBinding
import com.will.play.mine.ui.viewmodel.MineCooperateViewModel
import com.will.play.mine.ui.viewmodel.MineWalletViewModel

/**
 * Desc:我的合作
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineCooperateActivity : BaseActivity<MineActivityCooperateBinding, MineCooperateViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_cooperate
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