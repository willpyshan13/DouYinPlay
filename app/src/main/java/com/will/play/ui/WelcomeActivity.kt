package com.will.play.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.will.habit.utils.SPUtils
import com.will.play.R
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.base.widget.pager.BaseFragmentPagerAdapter
import com.will.play.ui.fragment.WelcomeFragment
import com.will.play.ui.main.TabBarActivity
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : FragmentActivity() {

    private val fragmentList = mutableListOf<Fragment>(WelcomeFragment(R.mipmap.base_icon_welcome_1),WelcomeFragment(R.mipmap.base_icon_welcome_1),WelcomeFragment(R.mipmap.base_icon_welcome_1))
    private val fragmentTitle = mutableListOf("","","")

    private val pagerAdapter = BaseFragmentPagerAdapter(supportFragmentManager,fragmentList,fragmentTitle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        welcome_viewpager.offscreenPageLimit = 3
        welcome_viewpager.adapter = pagerAdapter
//        launch({
//            delay(1000)
//            welcome_logo.visibility = View.GONE
//        })
        if (SPUtils.instance.getBoolean("first_init", true)) {
            startActivity(Intent(this, UserGuideActivity::class.java))
        } else {
            go2Home()
        }

        finish()
    }

    @LoginFilter
    private fun go2Home() {
        startActivity(Intent(this, TabBarActivity::class.java))
    }
}