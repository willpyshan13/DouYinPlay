package com.will.habit.widget.recycleview.footer

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * Desc: 加载更多样式接口
 */
interface IFooter {

    /**
     * Desc: 布局layoutId
     * <p>
     */
    @LayoutRes
    fun getLayoutResId(): Int

    /**
     * Desc: Footer绑定
     * <p>
     */
    fun onBindView(viewDataBinding: ViewDataBinding) {
        //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Desc: Footer移除
     * <p>
     */
    fun onDetached() {
        //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Desc: 用户判断是是否是当前Footer
     * <p>
     */
    fun handle(viewDataBinding: ViewDataBinding): Boolean

}