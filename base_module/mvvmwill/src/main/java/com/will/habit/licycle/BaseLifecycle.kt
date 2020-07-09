package com.will.habit.licycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

open class BaseLifecycle: LifecycleObserver {
    /**
     * 在LifecycleOwner的onCreate之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    /**
     * 在LifecycleOwner的onStart之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    /**
     * 在LifecycleOwner的onResume之后触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    /**
     * 在LifecycleOwner的onPause之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    /**
     * 在LifecycleOwner的onStop之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    /**
     * 在LifecycleOwner的onDestroy之前触发
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
    }

    /**
     * Desc:任意生命周期
     *
     *
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {
    }
}