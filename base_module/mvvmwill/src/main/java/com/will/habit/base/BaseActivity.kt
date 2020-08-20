package com.will.habit.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ktx.immersionBar
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity
import com.will.habit.R
import com.will.habit.BR
import com.will.habit.base.BaseViewModel.ParameterField
import com.will.habit.base.loading.LoadingDialogProvider
import com.will.habit.bus.Messenger
import com.will.habit.databinding.ActivityLayoutToolbarBinding
import com.will.habit.utils.StringUtils
import java.lang.reflect.ParameterizedType

/**
 * @author will
 */
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> : RxAppCompatActivity(), IBaseView {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM
    private var viewModelId = 0
    private var dialog: Dialog? = null

    /**
     * toolsbar  如果有添加的话
     */
    protected var bindingToolBar: ActivityLayoutToolbarBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState)
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        viewModel.registerRxBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel)
        viewModel.removeRxBus()
        if (::binding.isInitialized) {
            binding.unbind()
        }

        if (bindingToolBar != null) {
            bindingToolBar!!.unbind()
        }
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        viewModelId = initVariableId()
        var viewModel = initViewModel()
        if (viewModel == null) {
            val modelClass: Class<BaseViewModel<*>>
            val type = javaClass.genericSuperclass
            modelClass = if (type is ParameterizedType) {
                type.actualTypeArguments[1] as Class<BaseViewModel<*>>
            } else {
                BaseViewModel::class.java
            }
            viewModel = createViewModel(this, modelClass) as VM
        }
        this.viewModel = viewModel
        //关联ViewModel
        //让ViewModel拥有View的生命周期感应
        this.viewModel.let {
            lifecycle.addObserver(this.viewModel)
        }

        //注入RxLifecycle生命周期
        this.viewModel.injectLifecycleProvider(this)

    }

    //沉浸式布局
    protected fun needImmersion(): Boolean {
        return true
    }

    /**
     * 注入绑定
     */
    private fun initViewDataBinding(savedInstanceState: Bundle?) {
        if (needToolBar()) {
            /*
             * toolBar 动态设置
             */
            bindingToolBar = DataBindingUtil.setContentView(this, R.layout.activity_layout_toolbar)
            bindingToolBar!!.setVariable(BR.viewModel, viewModel)
            val base = window.decorView.findViewById<ConstraintLayout>(R.id.base_layout)
            binding = DataBindingUtil.inflate(LayoutInflater.from(this), initContentView(null), base, false)
            binding.setVariable(viewModelId, viewModel)

            val layoutParams = ConstraintLayout.LayoutParams(0, 0)
            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.topToBottom = R.id.toolbar
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            base.addView(binding.root, layoutParams)
        } else {
            //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
            binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState))
            binding.setVariable(viewModelId, viewModel)
        }
        binding.lifecycleOwner = this
        if (needImmersion()) {
            immersionBar {
                statusBarColor(R.color.translate)
                navigationBarColor(R.color.translate)
                statusBarDarkFont(true)
            }
        }
    }

    //刷新布局
    fun refreshLayout() {
        binding.setVariable(viewModelId, viewModel)
    }

    /**
     *
     * Desc:是否需要使用toolbar
     * <p>
     * Author: pengyushan
     * Date: 2020-06-24
     * @return Boolean
     */
    protected open fun needToolBar(): Boolean {
        return true
    }

    /**
     * =====================================================================
     */
    //注册ViewModel与View的契约UI回调事件
    protected fun registerUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.uc.showDialogEvent.observe(this, Observer {
            if (it != null) {
                this.showDialog(it)
            }
        })
        //加载对话框消失
        viewModel.uc.dismissDialogEvent!!.observe(this, Observer { dismissDialog() })
        //跳入新页面
        viewModel.uc.startActivityEvent!!.observe(this, Observer {
            val clz = it!![ParameterField.CLASS] as Class<*>?
            val bundle = it[ParameterField.BUNDLE] as Bundle?
            startActivity(clz, bundle)
        })
        //跳入ContainerActivity
        viewModel.uc.startContainerActivityEvent!!.observe(this, Observer {
            val canonicalName = it!![ParameterField.CANONICAL_NAME] as String?
            val bundle = it[ParameterField.BUNDLE] as Bundle?
            startContainerActivity(canonicalName, bundle)
        })
        //关闭界面
        viewModel.uc.finishEvent!!.observe(this, Observer { finish() })
        //关闭上一层
        viewModel.uc.onBackPressedEvent!!.observe(this, Observer { onBackPressed() })
    }

    protected open fun showDialog(title: String = StringUtils.getStringResource(R.string.common_wait_loading)) {
        if (dialog == null) {
            dialog = LoadingDialogProvider.createLoadingDialog(this, title)
        }
        dialog!!.setCancelable(false)
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.show()
    }

    protected open fun dismissDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
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
    fun startContainerActivity(canonicalName: String?, bundle: Bundle? = null) {
        val intent = Intent(this, ContainerActivity::class.java)
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName)
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle)
        }
        startActivity(intent)
    }

    /**
     * =====================================================================
     */
    override fun initParam() {}

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(savedInstanceState: Bundle?): Int

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
    open fun initViewModel(): VM? {
        return null
    }

    override fun initData() {}
    override fun initViewObservable() {}

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    protected open fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity,ViewModelProvider.AndroidViewModelFactory(application)).get(cls)
    }
}