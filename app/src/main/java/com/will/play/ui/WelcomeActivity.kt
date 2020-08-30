package com.will.play.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.will.play.R
import com.will.play.aop.login.annotation.LoginCallBackFilter
import com.will.play.aop.login.annotation.LoginFailedFilter
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.ui.main.TabBarActivity

class WelcomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        go2Home()
    }

    @LoginFilter
    private fun go2Home(){
        finish()
        startActivity(Intent(this,TabBarActivity::class.java))
    }

    @LoginCallBackFilter
    private fun loginResult(){
        finish()
        startActivity(Intent(this,TabBarActivity::class.java))
    }

    @LoginFailedFilter
    private fun loginFailure(){
        finish()
        startActivity(Intent(this,TabBarActivity::class.java))
    }
}