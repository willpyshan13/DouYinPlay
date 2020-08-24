package com.will.play.base.web

import android.webkit.JavascriptInterface
import androidx.annotation.Keep
import com.will.habit.constant.ConstantConfig
import com.will.habit.utils.SPUtils

/**
 * Desc: H5交互
 * <p>
 * Date: 2019/12/4
 * Copyright: Copyright (c) 2010-2019
 * Updater:
 * Update Time: 2019/12/4
 * Update Comments:
 *
 * @author: pengyushan
 */
@Keep
class WebViewBridge {

    /**
     * Desc: 获取Token
     * <p>
     * author: pengyushan
     * Date: 2019/12/4
     */
    @JavascriptInterface
    fun getToken(): String? {
        return SPUtils.instance.getString(ConstantConfig.AUTHORIZATION)
    }

}