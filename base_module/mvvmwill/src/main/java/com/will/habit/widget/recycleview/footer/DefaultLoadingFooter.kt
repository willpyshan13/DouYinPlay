package com.will.habit.widget.recycleview.footer

import androidx.databinding.ViewDataBinding
import com.will.habit.R
import com.will.habit.databinding.ListFooterLoadingBinding

/**
 * Desc: 默认加载中样式
 * <p>
 */
class DefaultLoadingFooter : IFooter {

    override fun getLayoutResId(): Int {
        return R.layout.list_footer_loading
    }

    override fun onBindView(viewDataBinding: ViewDataBinding) {
    }

    override fun onDetached() {
        super.onDetached()
    }

    override fun handle(viewDataBinding: ViewDataBinding): Boolean {
        return viewDataBinding is ListFooterLoadingBinding
    }
}