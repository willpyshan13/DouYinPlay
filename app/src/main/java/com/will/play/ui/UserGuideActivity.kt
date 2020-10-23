package com.will.play.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.R
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.ui.main.TabBarActivity


class UserGuideActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        go2Home()
        finish()
    }

    private fun showConfirm(){
        ConfirmDialog(this, true)
                .setTtitle("提示")
                .setMessage("每个身份只有一次选择机会，请慎重考虑")
                .setNegativeButtonColor(ContextCompat.getColor(this,R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(this,R.color.color_FFEA00))
                .setNegativeButton("再想想") { p0, p1 -> }
                .setPositiveButton("确定") { p0, p1 ->

                }.create().show();
    }


    private fun go2Home(){
        startActivity(Intent(this,TabBarActivity::class.java))
    }
}