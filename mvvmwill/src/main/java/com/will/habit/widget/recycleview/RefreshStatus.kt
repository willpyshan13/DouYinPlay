package com.will.habit.widget.recycleview

import androidx.annotation.IntDef
import com.will.habit.widget.recycleview.RefreshStatus.Companion.STATUS_NONE
import com.will.habit.widget.recycleview.RefreshStatus.Companion.STATUS_REFRESHING

/**
 * Desc: 刷新状态
 * <p>
 */
@IntDef(STATUS_NONE, STATUS_REFRESHING)
@Retention(AnnotationRetention.SOURCE)
annotation class RefreshStatus {

    companion object {
        /**
         * 无刷新状态
         */
        const val STATUS_NONE = 0
        /**
         * 刷新中
         */
        const val STATUS_REFRESHING = 1
        /**
         * 禁止刷新
         */
        const val STATUS_FORBID = 2
    }

}