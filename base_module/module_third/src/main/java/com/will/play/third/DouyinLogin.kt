package com.will.play.third

import android.app.Activity
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.will.habit.bus.event.SingleLiveEvent

object DouyinLogin {
    val authSuccess = SingleLiveEvent<String>()
    fun login(activity: Activity){
        val douyinOpenApi = DouYinOpenApiFactory.create(activity);
        val request = Authorization.Request();
        request.scope = "user_info,aweme.share,video.data,video.list,video.create,video.delete,video.comment,item.comment,im,mobile,mobile_alert,video.search,data.external.item,data.external.user,fans.data,hotsearch"
        // 用户授权时必选权限
        //request.state = "ww";                                 // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
        request.callerLocalEntry = "com.will.play.third.douyin.DouYinEntryActivity";
        douyinOpenApi.authorize(request);                    // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权
    }
}