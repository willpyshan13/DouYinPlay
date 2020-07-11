package com.will.habit.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.huantansheng.easyphotos.engine.ImageEngine;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class GlideEngine:ImageEngine {

    companion object{
        //单例
        private  var instance: GlideEngine = GlideEngine()

        //单例模式，私有构造方法
        private fun glideEngine() {}
        //获取单例
        @JvmStatic
        fun getInstance(): GlideEngine {
            return instance
        }
    }


    /**
     * 加载图片到ImageView
     *
     * @param context   上下文
     * @param uri 图片路径Uri
     * @param imageView 加载到的ImageView
     */
    //安卓10推荐uri，并且path的方式不再可用
    override fun loadPhoto(@NonNull context: Context, @NonNull uri: Uri, @NonNull imageView: ImageView) {
        Glide.with(context).load(uri).transition(withCrossFade()).into(imageView)
    }

    /**
     * 加载gif动图图片到ImageView，gif动图不动
     *
     * @param context   上下文
     * @param gifUri   gif动图路径Uri
     * @param imageView 加载到的ImageView
     *
     *
     * 备注：不支持动图显示的情况下可以不写
     */
    //安卓10推荐uri，并且path的方式不再可用
    override fun loadGifAsBitmap(@NonNull context: Context, @NonNull gifUri: Uri, @NonNull imageView: ImageView) {
        Glide.with(context).asBitmap().load(gifUri).into(imageView)
    }

    /**
     * 加载gif动图到ImageView，gif动图动
     *
     * @param context   上下文
     * @param gifUri   gif动图路径Uri
     * @param imageView 加载动图的ImageView
     *
     *
     * 备注：不支持动图显示的情况下可以不写
     */
    //安卓10推荐uri，并且path的方式不再可用
    override fun loadGif(@NonNull context: Context, @NonNull gifUri: Uri, @NonNull imageView: ImageView) {
        Glide.with(context).asGif().load(gifUri).transition(withCrossFade()).into(imageView)
    }


    /**
     * 获取图片加载框架中的缓存Bitmap
     *
     * @param context 上下文
     * @param uri    图片路径
     * @param width   图片宽度
     * @param height  图片高度
     * @return Bitmap
     * @throws Exception 异常直接抛出，EasyPhotos内部处理
     */
    //安卓10推荐uri，并且path的方式不再可用
    @Throws(Exception::class)
    override fun getCacheBitmap(@NonNull context: Context, @NonNull uri: Uri, width: Int, height: Int): Bitmap {
        return Glide.with(context).asBitmap().load(uri).submit(width, height).get()
    }

}