package com.will.habit.widget.recycleview.footer

import androidx.databinding.ViewDataBinding
import com.will.habit.R
import com.will.habit.databinding.ListFooterNoMoreBinding

/**
 * Desc: 默认无更多数据样式
 */
class DefaultNoMoreFooter : IFooter {

    override fun getLayoutResId(): Int {
        return R.layout.list_footer_no_more
    }

    override fun onBindView(viewDataBinding: ViewDataBinding) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun handle(viewDataBinding: ViewDataBinding): Boolean {
        return viewDataBinding is ListFooterNoMoreBinding
    }
}