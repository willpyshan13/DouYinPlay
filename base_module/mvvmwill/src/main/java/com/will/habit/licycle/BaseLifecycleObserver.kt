package com.will.habit.licycle

import androidx.lifecycle.LifecycleOwner

abstract class BaseLifecycleObserver(owner: LifecycleOwner) : BaseLifecycle() {

    private var mLifecycleOwner: LifecycleOwner? = null

    init {
        mLifecycleOwner = owner
        owner.lifecycle.addObserver(this)
    }

    override fun onDestroy() {
        mLifecycleOwner?.lifecycle?.removeObserver(this)
    }
}
