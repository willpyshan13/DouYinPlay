package com.will.play.pick.service

import com.will.habit.http.BaseResponse
import com.will.play.base.BannerEntity
import com.will.play.base.MineDouyinEntity
import com.will.play.base.constant.Constants
import com.will.play.pick.entity.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Desc:首页服务类
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
interface PickService {
    @GET("api.php/Index/ad")
    suspend fun getHomeBanner(@Query("group_id")pageNum:Int): BaseResponse<BannerEntity>

    @GET("api.php/GoodsType/index")
    suspend fun getGoodsType(@Query("pagesize")pageNum:Int): BaseResponse<PickGoodTypeEntity>

    @GET("api.php/Task/index")
    suspend fun getTaskIndex(@Query("page")page:Int,@Query("pagesize")pagesize:Int = 10
                             ,@Query("goods_type_id")goods_type_id:Int = 0,@Query("sort_id")sort_id:Int = 0
                             ,@Query("user_id")user_id:Int = 0
                             ,@Query("keyword")keyword:String? = null): BaseResponse<PickRespDataEntity>


    @GET("api.php/Task/detail")
    suspend fun getGoodDetail(@Query("id")id:String): BaseResponse<PickGoodDetailRespEntity>


    @GET("api.php/Task/download")
    suspend fun getTaskDownload(@Query("id")id:String): BaseResponse<PickGoodTypeEntity>

    @POST("${Constants.BASE_API}/Douyin/oauthCallback")
    suspend fun getDouyinoauthCallback(@Query("code") username: String?): BaseResponse<MineDouyinEntity>

    @GET("api.php/Video/index")
    suspend fun getVideoIndex(@Query("page")page:Int,@Query("pagesize")pagesize:Int = 20
                             ,@Query("goods_type_id")goods_type_id:String = "0",@Query("download_status")download_status:Int = 0): BaseResponse<PickRespCollectVideoEntity>

    @POST("api.php/DouyinVideo/index")
    suspend fun getDouyinVideoIndex(@Query("video_id")id:String): BaseResponse<PickDouyinEntity>

    @POST("api.php/Video/bind")
    suspend fun getVideoBind(@Query("video_id")video_id:String,@Query("douyin_video_id")douyin_video_id:String): BaseResponse<PickDouyinEntity>



}