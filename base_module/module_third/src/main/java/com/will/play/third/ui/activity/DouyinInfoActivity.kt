package com.will.play.third.ui.activity

import android.os.Bundle
import com.huantansheng.easyphotos.EasyPhotos
import com.will.habit.base.BaseActivity
import com.will.habit.utils.GlideEngine
import com.will.habit.utils.Utils
import com.will.habit.widget.dialog.ChoiceDialog
import com.will.play.third.R
import com.will.play.third.BR
import com.will.play.third.databinding.ThirdActivityDouyinInfoBinding
import com.will.play.third.ui.viewmodel.ThirdDouyinInfoViewModel


class DouyinInfoActivity : BaseActivity<ThirdActivityDouyinInfoBinding, ThirdDouyinInfoViewModel>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.third_activity_douyin_info
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()

    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.showChoice.observe(this,{
            ChoiceDialog(this,true).setItems(Utils.getContext().resources.getStringArray(R.array.douyin_info))
                    .hasCancleButton(true)
                    .setOnItemClickListener { textView, position ->
                       viewModel.typeValue = position+1
                        viewModel.value6.set(textView.text.toString())
                    }.create().show()
        })
    }
}