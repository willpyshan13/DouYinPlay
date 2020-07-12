package com.will.play.mine.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.huantansheng.easyphotos.EasyPhotos
import com.will.habit.base.BaseActivity
import com.will.habit.utils.GlideEngine
import com.will.habit.utils.MaterialDialogUtils
import com.will.habit.utils.StringUtils
import com.will.habit.utils.Utils
import com.will.habit.widget.dialog.ChoiceDialog
import com.will.play.mine.BR
import com.will.play.mine.R
import com.will.play.mine.databinding.MineActivityAddressBinding
import com.will.play.mine.databinding.MineActivityInfoEditBinding
import com.will.play.mine.ui.viewmodel.MineAddressViewModel
import com.will.play.mine.ui.viewmodel.MineInfoEditViewModel

/**
 * Desc:修改我的信息
 *
 * Date: 2020-06-30
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineInfoEditActivity : BaseActivity<MineActivityInfoEditBinding, MineInfoEditViewModel>() {

    private val requestGallery = 100

    private val requestCamera = 101

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.mine_activity_info_edit
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.takePhone.observe(this, Observer {
            ChoiceDialog(this,true).setItems(Utils.getContext().resources.getStringArray(R.array.mine_picture))
                    .hasCancleButton(true)
                    .setOnItemClickListener { _, position ->
                        when (position) {
                            0 -> EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
                                    .start(requestGallery);
                            else -> EasyPhotos.createCamera(this).setFileProviderAuthority("com.will").start(requestCamera)
                        }

                    }.create().show()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestGallery) {

        }
    }
}