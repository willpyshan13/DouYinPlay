package com.will.play.mine.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.will.habit.base.BaseActivity
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.ui.dialog.PrivacyDialog
import com.will.play.mine.ui.viewmodel.MineLoginViewModel
import com.will.play.third.DouyinLogin
import kotlinx.android.synthetic.main.mine_activity_login.*

/**
 * Desc:登陆页面
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineLoginActivity : BaseActivity<MineActivityAddressBinding, MineLoginViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_login
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun needToolBar(): Boolean {
        return false
    }

    override fun initData() {
        super.initData()
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.douyinLogin.observe(this, Observer {
            DouyinLogin.login(this)
        })

        viewModel.showPhoneCallDialog.observe(this, {
            val dialog = PrivacyDialog(this@MineLoginActivity)
            val tv_privacy_tips: TextView = dialog.findViewById(R.id.tv_privacy_tips)
            val btn_exit: TextView = dialog.findViewById(R.id.btn_exit)
            val btn_enter: TextView = dialog.findViewById(R.id.btn_enter)
            dialog.show()

            val string = "\n" +
                    "请你务必审慎，充分理解“服务协议”和“隐私政策”各条款，包括但不限于：为了向你提供即时通讯、内容分享等服务，我们需要手机你的" +
                    "设备信息、操作日志等个人信息。你可以在“我的”中查看相关条款说明" +
                    "你可阅读" +
                    "《用户服务协议》和《隐私协议》了解详情。如你同意，请点击\"同意\"开始接受我们的服务"
            val key1 = "《用户服务协议》"
            val key2 = "《隐私协议》"
            val index1 = string.indexOf(key1)
            val index2 = string.indexOf(key2)

            //需要显示的字串

            //需要显示的字串
            val spannedString = SpannableString(string)
            //设置点击字体颜色
            val colorSpan1 = ForegroundColorSpan(resources.getColor(R.color.color_036EB8))
            spannedString.setSpan(colorSpan1, index1, index1 + key1.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            val colorSpan2 = ForegroundColorSpan(resources.getColor(R.color.color_036EB8))
            spannedString.setSpan(colorSpan2, index2, index2 + key2.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            //设置点击字体大小
            val sizeSpan1 = AbsoluteSizeSpan(18, true)
            spannedString.setSpan(sizeSpan1, index1, index1 + key1.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            val sizeSpan2 = AbsoluteSizeSpan(18, true)
            spannedString.setSpan(sizeSpan2, index2, index2 + key2.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            //设置点击事件
            val clickableSpan1: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    val bundle = Bundle().apply {
                        putString(WebViewPath.URL,"http://test.weizhiyx.com/api.php/Webpage/argument")
                    }
                    startActivity(WebViewActivity::class.java,bundle)
                }

                override fun updateDrawState(ds: TextPaint) {

                }
            }
            spannedString.setSpan(clickableSpan1, index1, index1 + key1.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            val clickableSpan2: ClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    val bundle = Bundle().apply {
                        putString(WebViewPath.URL,"http://test.weizhiyx.com/api.php/Webpage/privacy")
                    }
                    startActivity(WebViewActivity::class.java,bundle)
                }

                override fun updateDrawState(ds: TextPaint) {
                    //点击事件去掉下划线
                    ds.isUnderlineText = false
                }
            }
            spannedString.setSpan(clickableSpan2, index2, index2 + key2.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)

            //设置点击后的颜色为透明，否则会一直出现高亮
            tv_privacy_tips.highlightColor = Color.TRANSPARENT
            //开始响应点击事件
            tv_privacy_tips.movementMethod = LinkMovementMethod.getInstance()

            tv_privacy_tips.text = spannedString

            //设置弹框宽度占屏幕的80%
            val m = windowManager
            val defaultDisplay = m.defaultDisplay
            val params = dialog.window!!.attributes
            params.width = (defaultDisplay.width * 0.80).toInt()
            dialog.window!!.attributes = params

            btn_exit.setOnClickListener {
                dialog.dismiss()
                finish()
            }

            btn_enter.setOnClickListener {
                dialog.dismiss()
                mine_check.isChecked = true
            }
        })
    }
}