package com.will.play.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import com.gyf.immersionbar.ktx.immersionBar
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.SPUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.ConfirmDialog
import com.will.play.R
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.ui.main.TabBarActivity
import kotlinx.android.synthetic.main.activity_guide.*


class UserGuideActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        immersionBar {
            statusBarColor(com.will.habit.R.color.translate)
            navigationBarColor(com.will.habit.R.color.translate)
            statusBarDarkFont(true)
        }
        guide_person_image.setOnCheckedChangeListener { p0, p1 ->
            guide_merchant_image.isChecked = !p1
            if (p1){
                guide_btn.setBackgroundResource(R.drawable.base_yellow_circle_btn)
                guide_btn.setTextColor(Color.BLACK)
            }
        }
        guide_merchant_image.setOnCheckedChangeListener { p0, p1 ->
            guide_person_image.isChecked = !p1
            if (p1){
                guide_btn.setBackgroundResource(R.drawable.base_blue_circle_btn)
                guide_btn.setTextColor(Color.WHITE)
            }
        }

        guide_btn.setOnClickListener {
            showConfirm()
        }
    }

    private fun showConfirm(){
        ConfirmDialog(this, true)
                .setTtitle("提示")
                .setMessage("每个身份只有一次选择机会，请慎重考虑")
                .setNegativeButtonColor(ContextCompat.getColor(this,R.color.color_E7E7E7))
                .setPositiveButtonColor(ContextCompat.getColor(this,R.color.color_FFEA00))
                .setNegativeButton("再想想") { p0, p1 -> }
                .setPositiveButton("确定") { p0, p1 ->
                    go2Home()
                    SPUtils.instance.put("first_init",false)
                }.create().show();
    }


    private fun go2Home(){
        startActivity(Intent(this,TabBarActivity::class.java))
        finish()
    }
}