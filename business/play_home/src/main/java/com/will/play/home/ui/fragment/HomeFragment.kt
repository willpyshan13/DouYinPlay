package com.will.play.home.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.will.habit.base.BaseFragment
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.SPUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.home.BR
import com.will.play.home.R
import com.will.play.home.databinding.FragmentHomeBinding
import com.will.play.home.ui.viewmodel.FragmentHomeViewModel

/**
 * Desc:home
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, FragmentHomeViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_home
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.updateUrl.observe(this,{
            if (it!=null) {
                AllenVersionChecker
                        .getInstance()
                        .downloadOnly(
                                UIData.create().setTitle("提示")
                                        .setContent("有新版本更新").setDownloadUrl(it)
                        )
                        .executeMission(context);
            }
        })

        viewModel.showChangeMerchant.observe(this,{
            showMerchantConfirm()
        })

        viewModel.showConfirmMerchant.observe(this,{
            showConfirm()
        })

        viewModel.showWechatMerchant.observe(this,{
            showWechatConfirm()
        })
    }

    private fun showConfirm(){
        ConfirmDialog(requireContext(), true)
                .setTtitle("确认身份")
                .setMessage("当前身份为非付费商家，暂不支持此权限")
                .setNegativeButtonColor(ContextCompat.getColor(requireContext(),R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(requireContext(),R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("升级商家") { p0, p1 ->
                    p0.dismiss()
                    showWechatConfirm()
                }.create().show();
    }

    private fun showMerchantConfirm(){
        ConfirmDialog(requireContext(), true)
                .setTtitle("确认身份")
                .setMessage("每个身份只有一次选择机会，请慎重考虑")
                .setNegativeButtonColor(ContextCompat.getColor(requireContext(),R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(requireContext(),R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("转换为商家") { p0, p1 ->
                    SPUtils.instance.put("first_init",false)
                }.create().show();
    }

    private fun showWechatConfirm(){
        ConfirmDialog(requireContext(), true)
                .setTtitle("添加客服转换身份")
                .setMessage("微信号码hjksadadhsadjk23\n复制到微信添加")
                .setNegativeButtonColor(ContextCompat.getColor(requireContext(),R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(requireContext(),R.color.color_FFEA00))
                .setNegativeButton("取消") { p0, p1 -> }
                .setPositiveButton("复制") { p0, p1 ->
                    ClipboardUtils.copyText("")
                }.create().show();
    }
}