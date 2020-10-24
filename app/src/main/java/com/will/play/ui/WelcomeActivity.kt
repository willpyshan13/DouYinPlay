package com.will.play.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.will.habit.utils.SPUtils
import com.will.play.R
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.ui.main.TabBarActivity


class WelcomeActivity : Activity() {
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
}