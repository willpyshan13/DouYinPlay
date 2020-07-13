package com.will.play.mine.ui.viewmodel

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.will.habit.base.ItemViewModel
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.utils.Utils
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
                item_type_vip -> viewModel.startActivity(MineVipActivity::class.java)
                item_type_wallet -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_message -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_binding_taobao -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_binding_douyin -> viewModel.startActivity(MineAddDouyinActivity::class.java)
                item_type_course -> viewModel.startActivity(MineWalletActivity::class.java)
                item_type_guide -> viewModel.startActivity(MineLoginActivity::class.java)
                item_type_contract -> viewModel.startActivity(MineWalletActivity::class.java)
                else -> viewModel.startActivity(MineWalletActivity::class.java)
            }
        }
    })

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