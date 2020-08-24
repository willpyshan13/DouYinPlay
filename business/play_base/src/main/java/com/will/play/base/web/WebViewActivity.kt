package com.will.play.base.web

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.WebSettings
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.will.play.base.BR
import com.will.habit.base.BaseActivity
import com.will.play.base.R
import com.will.play.base.databinding.BaseActivityWebBinding

/**
 * Desc: 通用浏览器
 * <p>
 * Date: 2019/12/2
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time: 2019/12/2
 * Update Comments:
 *
 * @author: pengyushan
 */
@Route(path = WebViewPath.WEB_VIEW_ACTIVITY)
class WebViewActivity : BaseActivity<BaseActivityWebBinding, WebViewModel>() {

    val url by lazy { intent.extras?.getString(WebViewPath.URL) }

    val title by lazy { intent.extras?.getString(WebViewPath.TITLE) }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.base_activity_web
    }

    override fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WebViewModel(application, url, title) as T
            }
        })[cls]
    }

    override fun initData() {
        super.initData()
        binding.webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        binding.webView.addJavascriptInterface(WebViewBridge(), "user")
    }

    /**
     * Desc: 打开相册
     * <p>
     * Date: 2020-02-19
     */
    private fun startAlbum() {
        val albumIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        albumIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(Intent.createChooser(albumIntent, "Image Chooser"), RESULT_CODE_PHOTO)
    }

    override fun initViewObservable() {
        super.initViewObservable()
        viewModel.photoSelect.observe(this, Observer {
            startAlbum()
        })
    }

    override fun onBackPressed() {
        if ( binding.webView.canGoBack()) {
            binding.webView.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri = data?.data
        if (requestCode == RESULT_CODE_PHOTO && resultCode == RESULT_OK && uri != null) {
            viewModel.filePathCallback?.onReceiveValue(arrayOf(uri))
        } else {
            viewModel.filePathCallback?.onReceiveValue(null)
        }
        viewModel.filePathCallback = null
    }

    companion object {
        private const val RESULT_CODE_PHOTO = 1024
    }
}