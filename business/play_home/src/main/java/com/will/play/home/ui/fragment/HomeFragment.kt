package com.will.play.home.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.UIData
import com.will.habit.base.BaseFragment
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.ToastUtils
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
    }
}