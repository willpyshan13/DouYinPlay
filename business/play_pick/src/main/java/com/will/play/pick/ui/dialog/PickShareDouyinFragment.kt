package com.will.play.pick.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.will.habit.utils.ClipboardUtils
import com.will.habit.utils.ToastUtils
import com.will.habit.widget.dialog.CommonBottomDialogFragment
import com.will.play.pick.R

class PickShareDouyinFragment(private val copyUrl: String):CommonBottomDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pick_share_bottom, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val copyTextView = view.findViewById<TextView>(R.id.pick_copy_url)
        copyTextView.text = copyUrl
        view.findViewById<TextView>(R.id.pick_copy).setOnClickListener {
            ClipboardUtils.copyText(copyUrl)
            ToastUtils.showShort("复制成功")
        }
        view.findViewById<TextView>(R.id.pick_copy_share).setOnClickListener {
            com.will.play.base.utils.PackageUtils.openDouyin(activity!!)
        }


    }

    override fun enableDragToClose(): Boolean {
        return false
    }
}