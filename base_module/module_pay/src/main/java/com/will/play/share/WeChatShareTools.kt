package com.will.play.share

import android.content.Context
import android.graphics.Bitmap
import com.tencent.mm.opensdk.modelmsg.*
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.will.play.tools.ImageTool
import java.text.SimpleDateFormat
import java.util.*


class WeChatShareTools {
    /**
     * 发送到聊天界面——WXSceneSession
     * 发送到朋友圈——WXSceneTimeline
     * 添加到微信收藏——WXSceneFavorite
     */
    private var iwxapi: IWXAPI? = null

    fun init(mContext: Context?, WX_APP_ID: String?) {
        iwxapi = WXAPIFactory.createWXAPI(mContext, WX_APP_ID, true)
        iwxapi?.registerApp(WX_APP_ID)
    }

    fun shareText(text: String?, sharePlace: SharePlace?) {
        val wxTextObject = WXTextObject()
        wxTextObject.text = text
        val msg = WXMediaMessage()
        msg.mediaObject = wxTextObject
        msg.description = text
        val req = SendMessageToWX.Req()
        req.transaction = getCurrTime()
        req.message = msg
        when (sharePlace) {
            SharePlace.Friend -> req.scene = SendMessageToWX.Req.WXSceneSession
            SharePlace.Zone -> req.scene = SendMessageToWX.Req.WXSceneTimeline
            SharePlace.Favorites -> req.scene = SendMessageToWX.Req.WXSceneFavorite
            else -> {
            }
        }
        if (iwxapi != null) {
            iwxapi!!.sendReq(req)
        } else {
            throw NullPointerException("请先调用WechatShare.init()方法")
        }
    }

    fun shareImage(bitmap: Bitmap, sharePlace: SharePlace?) {
        val wxImageObject = WXImageObject(bitmap)
        val msg = WXMediaMessage()
        msg.mediaObject = wxImageObject
        val thumbBmp: Bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
        bitmap.recycle()
        msg.thumbData = ImageTool.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG)
        val req = SendMessageToWX.Req()
        req.transaction = getCurrTime()
        req.message = msg
        when (sharePlace) {
            SharePlace.Friend -> req.scene = SendMessageToWX.Req.WXSceneSession
            SharePlace.Zone -> req.scene = SendMessageToWX.Req.WXSceneTimeline
            SharePlace.Favorites -> req.scene = SendMessageToWX.Req.WXSceneFavorite
            else -> {
            }
        }
        if (iwxapi != null) {
            iwxapi!!.sendReq(req)
        } else {
            throw NullPointerException("请先调用WechatShare.init()方法")
        }
    }

    fun shareMusic(shareModel: WechatShareModel, sharePlace: SharePlace?) {
        val wxMusicObject = WXMusicObject()
        wxMusicObject.musicUrl = shareModel.url
        val msg = WXMediaMessage()
        msg.mediaObject = wxMusicObject
        msg.title = shareModel.title
        msg.description = shareModel.description
        msg.thumbData = shareModel.thumbData
        val req = SendMessageToWX.Req()
        req.transaction = getCurrTime()
        req.message = msg
        when (sharePlace) {
            SharePlace.Friend -> req.scene = SendMessageToWX.Req.WXSceneSession
            SharePlace.Zone -> req.scene = SendMessageToWX.Req.WXSceneTimeline
            SharePlace.Favorites -> req.scene = SendMessageToWX.Req.WXSceneFavorite
            else -> {
            }
        }
        if (iwxapi != null) {
            iwxapi!!.sendReq(req)
        } else {
            throw NullPointerException("请先调用WechatShare.init()方法")
        }
    }

    fun shareVideo(shareModel: WechatShareModel, sharePlace: SharePlace?) {
        val wxVideoObject = WXVideoObject()
        wxVideoObject.videoUrl = shareModel.url
        val msg = WXMediaMessage(wxVideoObject)
        msg.title = shareModel.title
        msg.description = shareModel.description
        msg.thumbData = shareModel.thumbData
        val req = SendMessageToWX.Req()
        req.transaction = getCurrTime()
        req.message = msg
        when (sharePlace) {
            SharePlace.Friend -> req.scene = SendMessageToWX.Req.WXSceneSession
            SharePlace.Zone -> req.scene = SendMessageToWX.Req.WXSceneTimeline
            SharePlace.Favorites -> req.scene = SendMessageToWX.Req.WXSceneFavorite
            else -> {
            }
        }
        if (iwxapi != null) {
            iwxapi!!.sendReq(req)
        } else {
            throw NullPointerException("请先调用WechatShare.init()方法")
        }
    }

    fun shareURL(shareModel: WechatShareModel, sharePlace: SharePlace?) {
        val webpageObject = WXWebpageObject()
        webpageObject.webpageUrl = shareModel.url
        val msg = WXMediaMessage(webpageObject)
        msg.title = shareModel.title
        msg.description = shareModel.description
        msg.thumbData = shareModel.thumbData
        val req = SendMessageToWX.Req()
        req.transaction = getCurrTime()
        req.message = msg
        when (sharePlace) {
            SharePlace.Friend -> req.scene = SendMessageToWX.Req.WXSceneSession
            SharePlace.Zone -> req.scene = SendMessageToWX.Req.WXSceneTimeline
            SharePlace.Favorites -> req.scene = SendMessageToWX.Req.WXSceneFavorite
            else -> {
            }
        }
        if (iwxapi != null) {
            iwxapi!!.sendReq(req)
        } else {
            throw NullPointerException("请先调用 WechatShare.init()方法")
        }
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    fun getCurrTime(): String? {
        val now = Date()
        val outFormat = SimpleDateFormat("yyyyMMddHHmmss")
        return outFormat.format(now)
    }

    enum class SharePlace {
        Friend, Zone, Favorites
    }
}