package com.will.play.mine.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.will.habit.base.BaseFragment
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.habit.widget.dialog.interfaces.ConfirmDialogInterface
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.FragmentMineBinding
import com.will.play.mine.ui.viewmodel.MineViewModel

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
class MineFragment : BaseFragment<FragmentMineBinding, MineViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_mine
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showPhoneCallDialog.observe(this, {
            ConfirmDialog(requireActivity(), true)
                    .setTtitle("提示")
                    .setMessage("有问题可以联系微信客服\na18030144436")
                    .setNegativeButton("取消") { p0, p1 -> }
                    .setPositiveButton("复制微信") { p0, p1 ->
                        ClipboardUtils.copyText("a18030144436")
                        ToastUtils.showShort("微信已经复制到剪贴板")
                    }.create().show();
        })
    }
}