package com.will.habit.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author will
 *
 */
open class BaseModel<T> : BaseScopeModel() {
    private var mView: T? = null

    /**
     * 管理RxJava，主要针对RxJava异步操作造成的内存泄漏
     */
    private var mCompositeDisposable: CompositeDisposable?

    /**
     * Desc:管理BaseView层
     */
    fun setVm(v: T) {
        mView = v
    }

    /**
     * Desc:管理Disposable
     *
     *
     */
    fun addSubscribe(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        //ViewModel销毁时会执行，同时取消所有异步任务
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    init {
        mCompositeDisposable = CompositeDisposable()
    }
}