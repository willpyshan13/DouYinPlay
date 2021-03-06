package com.will.play.data.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.will.habit.base.BaseFragment
import com.will.play.data.BR
import com.will.play.data.R
import com.will.play.data.databinding.FragmentDataBinding
import com.will.play.data.ui.viewmodel.DataViewModel
import com.will.play.third.DouyinLogin

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
class DataFragment : BaseFragment<FragmentDataBinding, DataViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_data
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.go2Video.observe(this, Observer {
//            Router.build("collect").go(this)
        })

        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(requireActivity())
        })

        DouyinLogin.authSuccess.observe(this, Observer {
            if (it!=null) {
                viewModel.getDouyinUserinfo(it)
            }
        })

    }
}