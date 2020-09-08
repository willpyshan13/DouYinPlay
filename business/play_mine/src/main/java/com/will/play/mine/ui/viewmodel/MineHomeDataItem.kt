package com.will.play.mine.ui.viewmodel

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.constant.ConstantConfig
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.SPUtils
import com.will.habit.utils.Utils
import com.will.play.aop.login.annotation.LoginFilter
import com.will.play.base.web.WebViewActivity
import com.will.play.base.web.WebViewPath
import com.will.play.mine.ui.activity.*

/**
 * 设置中心，item
 */
class MineHomeDataItem(viewModel: MineViewModel, private val resIcon: Int, val title: String, itemType: Int) : ItemViewModel<MineViewModel>(viewModel) {
    val imageDrawable = ObservableField<Drawable>()

    init {
        imageDrawable.set(ContextCompat.getDrawable(Utils.getContext(), resIcon))
    }

    val itemClick = BindingCommand<Any>(object : BindingAction {
        override fun call() {
            when (itemType) {
                item_type_vip -> gotoVipActivity()
                item_type_wallet -> gotoMineWalletActivity()
                item_type_message -> gotoMineWalletActivity()
                item_type_binding_taobao -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_binding_douyin -> viewModel.startActivity(MineAddDouyinActivity::class.java)
                item_type_course -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_guide -> {
                    val bundle = Bundle().apply {
                        putString(WebViewPath.URL,"${RetrofitClient.baseUrl}/api.php/Webpage/guide")
                    }
                    viewModel.startActivity(WebViewActivity::class.java,bundle)
                }
                item_type_contract -> {
                    val bundle = Bundle().apply {
                    putString(WebViewPath.URL,"${RetrofitClient.baseUrl}/api.php/Webpage/contact")
                }
                    viewModel.startActivity(WebViewActivity::class.java,bundle)
                }
                item_type_custom_service -> {
                    viewModel.showPhoneCallDialog.call()
                }
                else -> viewModel.startActivity(MineWalletActivity::class.java)
            }
        }
    })

    private fun gotoVipActivity(){
        viewModel.startActivity(MineVipActivity::class.java)
    }

    private fun gotoMineWalletActivity(){
        viewModel.startActivity(MineWalletActivity::class.java)
    }


    companion object {
        const val item_type_wallet = 0
        const val item_type_message = 1
        const val item_type_binding_taobao = 2
        const val item_type_binding_douyin = 3
        const val item_type_course = 4
        const val item_type_guide = 5
        const val item_type_contract = 6
        const val item_type_custom_service = 7
        const val item_type_vip = 8
    }
}