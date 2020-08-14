package com.will.play.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.will.play.BR
import com.will.play.R
import com.will.habit.base.BaseActivity
import com.will.play.aop.login.annotation.LoginFailedFilter
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.aop.login.core.LoginSDK
import com.will.play.databinding.ActivityTabBarBinding
import com.will.play.data.ui.fragment.DataFragment
import com.will.play.home.ui.fragment.HomeFragment
import com.will.play.mine.ui.fragment.MineFragment
import com.will.play.pick.ui.fragment.PickFragment
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import java.util.*

/**
 *
 * Desc:首页
 * <p>
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018 - 2020
 * Updater:
 * Update Time:
 * Update Comments:
 * @property mFragments MutableList<Fragment>?
 *
 * Author: pengyushan
 */
class TabBarActivity : BaseActivity<ActivityTabBarBinding, TabBarViewModel>() {
    private var mFragments: MutableList<Fragment>? = null
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_tab_bar
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun needToolBar(): Boolean {
        return false
    }

    override fun initData() {
        //初始化Fragment
        initFragment()
        //初始化底部Button
        initBottomTab()
    }

    private fun initFragment() {
        mFragments = ArrayList()
        mFragments!!.add(HomeFragment())
        mFragments!!.add(DataFragment())
        mFragments!!.add(PickFragment())
        mFragments!!.add(MineFragment())
        //默认选中第一个
        commitAllowingStateLoss(0)
    }

    private fun initBottomTab() {
        val navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.base_icon_home, R.mipmap.base_icon_home_select, "首页", resources.getColor(R.color.color_FDEC83))
                .addItem(R.mipmap.base_icon_data, R.mipmap.base_icon_data_select, "数据", resources.getColor(R.color.color_FDEC83))
                .addItem(R.mipmap.base_icon_pick, "选品", resources.getColor(R.color.color_FDEC83))
                .addItem(R.mipmap.base_icon_mine, "我的", resources.getColor(R.color.color_FDEC83))
                .build()
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
//                if (index == 3) {
//                    val isLogin = LoginSDK.instance?.isLogin(this@TabBarActivity)
//                    if (isLogin != null && !isLogin) {
//                        navigationController.setSelect(old)
//                    }
//                    checkLogin(index)
//                } else {
                    commitAllowingStateLoss(index)
//                }
            }

            override fun onRepeat(index: Int) {}
        })
    }

    @LoginFilter()
    private fun checkLogin(position: Int) {
        commitAllowingStateLoss(position)
    }

    private fun commitAllowingStateLoss(position: Int) {
        hideAllFragment()
        val transaction = supportFragmentManager.beginTransaction()
        var currentFragment = supportFragmentManager.findFragmentByTag(position.toString() + "")
        if (currentFragment != null) {
            transaction.show(currentFragment)
        } else {
            currentFragment = mFragments!![position]
            transaction.add(R.id.frameLayout, currentFragment, position.toString() + "")
        }
        transaction.commitAllowingStateLoss()
    }

    //隐藏所有Fragment
    private fun hideAllFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        for (i in mFragments!!.indices) {
            val currentFragment = supportFragmentManager.findFragmentByTag(i.toString() + "")
            if (currentFragment != null) {
                transaction.hide(currentFragment)
            }
        }
        transaction.commitAllowingStateLoss()
    }
}