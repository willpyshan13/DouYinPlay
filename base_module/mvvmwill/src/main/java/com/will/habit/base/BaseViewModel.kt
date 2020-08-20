package com.will.habit.base

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.trello.rxlifecycle4.LifecycleProvider
import com.will.habit.binding.command.BindingAction
import com.will.habit.binding.command.BindingCommand
import com.will.habit.bus.event.SingleLiveEvent
import com.will.habit.utils.Tutil
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import java.lang.ref.WeakReference
import java.util.*

/**
 * @author will
 *
 */
open class BaseViewModel<M : BaseModel<*>?> @JvmOverloads constructor(application: Application) : AndroidViewModel(application), IBaseViewModel, Consumer<Disposable?> {
     val uc by lazy(LazyThreadSafetyMode.NONE) { UIChangeLiveData() }

    //标题文字
    var titleText = ObservableField("")

    //右边文字
    var rightText = ObservableField("更多")

    //右边文字的观察者
    var rightTextVisibleObservable = ObservableInt(View.GONE)

    //右边图标的观察者
    var rightIconVisibleObservable = ObservableInt(View.GONE)

    //弱引用持有
    private var lifecycle: WeakReference<LifecycleProvider<*>>? = null

    //管理RxJava，主要针对RxJava异步操作造成的内存泄漏
    private var mCompositeDisposable: CompositeDisposable?
    protected fun addSubscribe(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable!!)
    }

    val model: M by lazy {
        Tutil.getNewInstance<M>(this, 0).apply {
            val baseView = this@BaseViewModel
            if (baseView is BaseView) {
                val model = this as BaseModel<BaseView>
                model.setVm(baseView)
            }
        }
    }

    /**
     * 注入RxLifecycle生命周期
     *
     * @param lifecycle
     */
    fun injectLifecycleProvider(lifecycle: LifecycleProvider<*>) {
        this.lifecycle = WeakReference(lifecycle)
    }

    val lifecycleProvider: LifecycleProvider<*>
        get() = lifecycle!!.get()!!

    @JvmOverloads
    fun showDialog(title: String = "loading...") {
        uc.showDialogEvent.postValue(title)
    }

    fun dismissDialog() {
        uc.dismissDialogEvent!!.call()
    }
    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    @JvmOverloads
    fun startActivity(clz: Class<*>, bundle: Bundle? = null) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uc.startActivityEvent!!.postValue(params)
    }
    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    @JvmOverloads
    fun startContainerActivity(canonicalName: String, bundle: Bundle? = null) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CANONICAL_NAME] = canonicalName
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uc.startContainerActivityEvent!!.postValue(params)
    }

    /**
     * 关闭界面
     */
    fun finish() {
        uc.finishEvent!!.call()
    }

    /**
     * 返回上一层
     */
    fun onBackPressed() {
        uc.onBackPressedEvent!!.call()
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {}
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}
    override fun registerRxBus() {}
    override fun removeRxBus() {}
    override fun onCleared() {
        super.onCleared()
        model?.let {
            model!!.onCleared()
        }
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    @Throws(Exception::class)
    override fun accept(disposable: Disposable?) {
        addSubscribe(disposable)
    }

    class UIChangeLiveData {
        val showDialogEvent by lazy(LazyThreadSafetyMode.NONE) { SingleLiveEvent<String>() }
        var dismissDialogEvent: SingleLiveEvent<Void>? = null
            get() = createLiveData(field).also { field = it }
            private set
        var startActivityEvent: SingleLiveEvent<Map<String, Any>>? = null
            get() = createLiveData(field).also { field = it }
            private set
        var startContainerActivityEvent: SingleLiveEvent<Map<String, Any>>? = null
            get() = createLiveData(field).also { field = it }
            private set
        var finishEvent: SingleLiveEvent<Void>? = null
            get() = createLiveData(field).also { field = it }
            private set
        var onBackPressedEvent: SingleLiveEvent<Void>? = null
            get() = createLiveData(field).also { field = it }
            private set

        private fun <T> createLiveData(liveData: SingleLiveEvent<T>?): SingleLiveEvent<T> {
            var liveData = liveData
            if (liveData == null) {
                liveData = SingleLiveEvent()
            }
            return liveData
        }

    }

    /**
     * 设置标题
     *
     * @param text 标题文字
     */
    open fun setTitleText(text: String?) {
        titleText.set(text)
    }

    /**
     * 设置右边文字
     *
     * @param text 右边文字
     */
    open fun setRightText(text: String?) {
        rightText.set(text)
    }

    /**
     * 设置右边文字的显示和隐藏
     *
     * @param visibility
     */
    open fun setRightTextVisible(visibility: Int) {
        rightTextVisibleObservable.set(visibility)
    }

    /**
     * 设置右边图标的显示和隐藏
     *
     * @param visibility
     */
    open fun setRightIconVisible(visibility: Int) {
        rightIconVisibleObservable.set(visibility)
    }

    /**
     * 返回按钮的点击事件
     */
    val backOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            finish()
        }
    })

    var rightTextOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            rightTextOnClick()
        }
    })
    var rightIconOnClick: BindingCommand<*> = BindingCommand<Any?>(object : BindingAction {
        override fun call() {
            rightIconOnClick()
        }
    })

    /**
     * 右边文字的点击事件，子类可重写
     */
    protected open fun rightTextOnClick() {}

    /**
     * 右边图标的点击事件，子类可重写
     */
    protected open fun rightIconOnClick() {}

    object ParameterField {
        @JvmField
        var CLASS = "CLASS"
        @JvmField
        var CANONICAL_NAME = "CANONICAL_NAME"
        @JvmField
        var BUNDLE = "BUNDLE"
    }

    init {
        mCompositeDisposable = CompositeDisposable()
    }
}