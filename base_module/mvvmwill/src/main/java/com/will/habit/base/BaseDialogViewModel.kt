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
open class BaseDialogViewModel<M : BaseModel<*>>(application: Application) : AndroidViewModel(application), IBaseViewModel {

    val uc: UIChangeLiveData by lazy(LazyThreadSafetyMode.NONE) { UIChangeLiveData() }

    @Suppress("UNCHECKED_CAST")
    val model: M by lazy {
        Tutil.getNewInstance<M>(this, 0).apply {
            val baseView = this@BaseDialogViewModel
            if (baseView is BaseView) {
                val model = this as BaseModel<BaseView>
                model.setVm(baseView)
            }
        }
    }

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

    @JvmOverloads
    fun showDialog(title: String = StringUtils.getStringResource(R.string.common_wait_loading)) {
        uc.showDialogEvent.value = title
    }

    fun dismissDialog() {
        uc.dismissDialogEvent.call()
    }

    /**
     * 关闭界面
     */
    open fun finish() {
        uc.finishEvent.call()
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    @JvmOverloads
    fun startActivity(clz: Class<*>, bundle: Bundle? = null) {
        val params = HashMap<String, Any>()
        params[BaseViewModel.ParameterField.CLASS] = clz
        if (bundle != null) {
            params[BaseViewModel.ParameterField.BUNDLE] = bundle
        }
        uc.startActivityEvent.postValue(params)
    }


    class UIChangeLiveData {
        val showDialogEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<String>() }
        val dismissDialogEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Void>() }
        val startActivityEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Map<String, Any>>() }
        val finishEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<Void>() }
    }

}
