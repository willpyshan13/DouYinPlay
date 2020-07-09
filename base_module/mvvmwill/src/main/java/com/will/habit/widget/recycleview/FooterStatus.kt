package com.will.habit.widget.recycleview

import androidx.annotation.IntDef
import com.will.habit.widget.recycleview.FooterStatus.Companion.STATUS_LOADING
import com.will.habit.widget.recycleview.FooterStatus.Companion.STATUS_NO_MORE
import com.will.habit.widget.recycleview.FooterStatus.Companion.STATUS_NONE

@IntDef(STATUS_LOADING, STATUS_NO_MORE, STATUS_NONE)
@Retention(AnnotationRetention.SOURCE)
annotation class FooterStatus{

    companion object{
        /**
         * 去掉所有加载状态
         */
        const val STATUS_NONE = 0
        /**
         * 显示正在加载中状态
         */
        const val STATUS_LOADING = 1
        /**
         * 显示没有更多数据加载的状态
         */
        const val STATUS_NO_MORE = 2
    }

}