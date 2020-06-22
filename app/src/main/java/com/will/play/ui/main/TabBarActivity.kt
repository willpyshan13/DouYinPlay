package com.will.play.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.will.play.BR
import com.will.play.R
import com.will.habit.base.BaseActivity
import com.will.play.databinding.ActivityTabBarBinding
import com.will.play.home.ui.fragment.TabBar1Fragment
import com.will.play.home.ui.fragment.TabBar2Fragment
import com.will.play.home.ui.fragment.TabBar3Fragment
import com.will.play.home.ui.fragment.TabBar4Fragment
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

    override fun initData() {
        //初始化Fragment
        initFragment()
        //初始化底部Button
        initBottomTab()
    }

    private fun initFragment() {
        mFragments = ArrayList()
        mFragments!!.add(TabBar1Fragment())
        mFragments!!.add(TabBar2Fragment())
        mFragments!!.add(TabBar3Fragment())
        mFragments!!.add(TabBar4Fragment())
        //默认选中第一个
        commitAllowingStateLoss(0)
    }

    private fun initBottomTab() {
        val navigationController = binding!!.pagerBottomTab.material()
                .addItem(R.mipmap.yingyong, "首页")
                .addItem(R.mipmap.huanzhe, "数据")
                .addItem(R.mipmap.xiaoxi_select, "选品")
                .addItem(R.mipmap.wode_select, "我的")
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build()
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frameLayout, mFragments.get(index));
//                transaction.commitAllowingStateLoss();
                commitAllowingStateLoss(index)
            }

            override fun onRepeat(index: Int) {}
        })
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