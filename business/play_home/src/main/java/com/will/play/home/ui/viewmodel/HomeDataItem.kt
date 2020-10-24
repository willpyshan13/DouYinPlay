package com.will.play.home.ui.viewmodel

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.will.habit.base.ItemViewModel
import com.will.play.home.R
import com.will.play.home.entity.DataLists

class HomeDataItem(viewModel:FragmentHomeViewModel,val data: DataLists,val index:Int) :ItemViewModel<FragmentHomeViewModel>(viewModel) {
    val positionDrawable = ObservableField<Drawable>()
    val positionVisible = ObservableInt(View.VISIBLE)
    init {
        when (index) {
            0 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_one))
            }
            1 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_two))
            }
            2 -> {
                positionVisible.set(View.VISIBLE)
                positionDrawable.set(ContextCompat.getDrawable(viewModel.getApplication(), R.mipmap.base_icon_three))
            }
            else -> {
                positionVisible.set(View.GONE)
            }
        }

    }
}