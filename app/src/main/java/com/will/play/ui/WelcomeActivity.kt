package com.will.play.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.will.habit.base.BaseActivity
import com.will.habit.extection.launch
import com.will.habit.utils.SPUtils
import com.will.play.R
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.base.widget.pager.BaseFragmentPagerAdapter
import com.will.play.databinding.ActivityWelcomeBinding
import com.will.play.mine.BR
import com.will.play.ui.fragment.WelcomeFragment
import com.will.play.ui.main.TabBarActivity
import com.will.play.ui.viewmodel.WelcomeViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.coroutines.delay


class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
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

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_welcome
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }
}