package com.will.play.pick.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.will.habit.widget.dialog.CommonBottomDialogFragment
import com.will.play.pick.R

class PickShareDouyinFragment:CommonBottomDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pick_share_bottom,null)
    }

    override fun enableDragToClose(): Boolean {
        return false
    }
}