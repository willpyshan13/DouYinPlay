package com.will.habit.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.will.habit.R
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.utils.StringUtils
import com.will.habit.utils.Tutil
import java.util.HashMap


/**
 * Desc:
 *
 * Date: 2020-07-12 12:47
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
open class BaseDialogViewModel<M : BaseModel<*>>(application: Application) : BaseViewModel<M>(application), IBaseViewModel {

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPause() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerRxBus() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeRxBus() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCleared() {
        super.onCleared()
        model.onCleared()
    }


    class UIChangeLiveData {
        val showDialogEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<String>() }
        val dismissDialogEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Void>() }
        val startActivityEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Map<String, Any>>() }
        val finishEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Void>() }
    }

}
