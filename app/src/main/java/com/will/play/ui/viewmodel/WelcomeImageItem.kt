package com.will.play.ui.viewmodel

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import com.will.habit.base.BaseViewModel
import com.will.habit.base.ItemViewModel

/**
 * 绑定抖音  item
 */
class WelcomeImageItem(viewModel: BaseViewModel<*>, val data:Int) : ItemViewModel<BaseViewModel<*>>(viewModel) {
    val imageDrawable = ObservableField<Drawable>(ContextCompat.getDrawable(viewModel.getApplication(),data))
}