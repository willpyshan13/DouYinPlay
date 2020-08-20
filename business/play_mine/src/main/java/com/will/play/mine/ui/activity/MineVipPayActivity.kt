package com.will.play.mine.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.will.habit.base.BaseActivity
import com.will.habit.constant.ConstantConfig
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityVipPayBinding
import com.will.play.mine.entity.UpgradeLists
import com.will.play.mine.ui.viewmodel.MineVipPayViewModel
import com.will.play.pay.WillPay
import com.will.play.pay.callback.IPayCallback
import com.will.play.pay.wechat.wxpay.WXPay
import com.will.play.pay.wechat.wxpay.WXPayInfoImpl

/**
 * Desc:vip详情
 *
 * Date: 2020-07-02
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineVipPayActivity : BaseActivity<MineActivityVipPayBinding, MineVipPayViewModel>() {
    private val wxPay: WXPay = WXPay.instance
    private val wxPayInfoImpl = WXPayInfoImpl()

    private val payMoney by lazy { intent.extras?.getParcelable(ConstantConfig.VIP_PAY_MONEY) as? UpgradeLists }

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_vip_pay
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MineVipPayViewModel(activity.application, payMoney) as T
            }
        }).get(cls)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.payClick.observe(this, Observer {
            if (it != null) {
                wxPayInfoImpl.appid = it.payInfo.appid
                wxPayInfoImpl.nonceStr = it.payInfo.noncestr
                wxPayInfoImpl.packageValue = it.payInfo.`package`
                wxPayInfoImpl.partnerid = it.payInfo.partnerid
                wxPayInfoImpl.prepayId = it.payInfo.prepayid
                wxPayInfoImpl.sign = it.payInfo.sign
            }
            WillPay.pay(wxPay, this, wxPayInfoImpl, object : IPayCallback {
                override fun failed(code: Int, message: String?) {
                }

                override fun cancel() {
                }

                override fun success() {
                }

            })
        })

    }
}