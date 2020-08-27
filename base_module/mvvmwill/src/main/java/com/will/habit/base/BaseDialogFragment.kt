package com.will.habit.base

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import com.will.habit.widget.dialog.CommonDialogFragment


import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.will.habit.R
import com.will.habit.bus.Messenger
import com.will.habit.utils.StringUtils
import java.lang.reflect.ParameterizedType

/**
 * Desc:基础DialogFragment 支持Databinding
 *
 * Date: 2020-07-12 12:36
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: zhuanghongzhan
 */
abstract class BaseDialogFragment<V : ViewDataBinding, VM : BaseDialogViewModel<*>> : CommonDialogFragment(), IBaseView {
    protected var binding: V? = null
    protected lateinit var viewModel: VM
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, true)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        registerUiChangeLiveDataCallback()
        //注册RxBus
        viewModel.registerRxBus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel)
        if (binding != null) {
            binding!!.unbind()
            //解除ViewModel生命周期感应
            lifecycle.removeObserver(viewModel)
            viewModel.removeRxBus()
        }
    }

    /**
     * 注入绑定
     */
    @Suppress("UNCHECKED_CAST")
    private fun initViewDataBinding() {
        val viewModelId = initVariableId()
        var viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<BaseDialogViewModel<*>>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<BaseDialogViewModel<*>>
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                BaseDialogViewModel::class.java
            }
            viewModel = createViewModel(this, modelClass) as VM
        }
        this.viewModel = viewModel
        binding!!.setVariable(viewModelId, viewModel)
        binding!!.lifecycleOwner = this
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
    }

    override fun initParam() {
        //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    @SuppressLint("FragmentLiveDataObserve")
    private fun registerUiChangeLiveDataCallback() {
        /*
         *加载loading Dialog
         */
        viewModel.uc.showDialogEvent.observe(this, Observer { this.showDialog(it) })
        /*
         * 加载对话框消失
        */

        viewModel.uc.dismissDialogEvent?.observe(this, Observer { dismissDialog() })
        /*
         * 跳入新页面
         */
        viewModel.uc.startActivityEvent?.observe(this, Observer { params ->
            if (params == null) {
                return@Observer
            }
            val clz = params[BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle
            startActivity(clz, bundle)
        })

        /*
        * 关闭界面
        */
        viewModel.uc.finishEvent?.observe(this, Observer { dismissAllowingStateLoss() })
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    protected open fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * Desc:显示loading Dialog
     *
     *
     *
     * @param title 显示文案
     */
    protected open fun showDialog(title: String? = StringUtils.getStringResource(R.string.common_wait_loading)) {
//        if (loadingDialog == null) {
//            loadingDialog = LoadingDialogProvider.createLoadingDialog(activity!!, title)
//        }
//        loadingDialog!!.setCancelable(false)
//        loadingDialog!!.setCanceledOnTouchOutside(false)
//        loadingDialog!!.show()
//        loadingDialog!!.setCancelable(false)
//        loadingDialog!!.show()
    }

    /**
     * Desc:显示可取消loading Didlog
     *
     *
     * @param title 显示文案
     * @param isCancelable 是否阻塞页面
     * @param isCancelOutsid 是否可以点击外部销毁
     * @param onCancelListener dialog 取消监听
     */
    protected open fun showDialog(title: String = StringUtils.getStringResource(R.string.common_wait_loading)
                                  , isCancelable: Boolean
                                  , isCancelOutsid: Boolean
                                  , onCancelListener: DialogInterface.OnCancelListener?) {
//        if (loadingDialog == null) {
//            loadingDialog = LoadingDialogProvider.createLoadingDialog(activity!!, title)
//        }
//        loadingDialog!!.setCancelable(isCancelable)
//        loadingDialog!!.setCanceledOnTouchOutside(isCancelOutsid)
//        loadingDialog!!.setOnCancelListener(onCancelListener)
//        loadingDialog!!.show()
    }

    protected open fun dismissDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }

    /**
     * Desc:构建布局
     *
     *
     * @param inflater           view inflation
     * @param container          容器
     * @param savedInstanceState A mapping from String keys to various
     * @return int 资源
     */
    abstract fun initContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    private fun initViewModel(): VM? {
        return null
    }

    override fun initData() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewObservable() {
        //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * 创建ViewModel
     *
     * @param <T>      泛型
     * @param fragment 父类
     * @param cls      需要創建的VM
     * @return VM
    </T> */
    protected open fun <T : BaseDialogViewModel<*>> createViewModel(fragment: Fragment, cls: Class<T>): T {
        return ViewModelProviders.of(fragment).get(cls)
    }
}
