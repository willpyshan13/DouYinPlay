package com.will.play.data.service

import com.will.habit.http.BaseResponse
import com.will.play.base.constant.Constants
import com.will.play.base.entity.BannerEntity
import com.will.play.base.entity.MineDouyinEntity
import com.will.play.data.entity.DataRecommendEntity
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
interface DataService {
    @GET("api.php/Index/ad")
    suspend fun getHomeBanner(@Query("group_id")pageNum:Int): BaseResponse<BannerEntity>


    @POST("api.php/Task/recommend")
    suspend fun getTaskRecommend(@Query("sort_id")sort_id:Int = 1,@Query("page")page:Int = 1,@Query("pagesize")pagesize:Int = 1): BaseResponse<DataRecommendEntity>

    @POST("${Constants.BASE_API}/Douyin/oauthCallback")
    suspend fun getDouyinoauthCallback(@Query("code") username: String?): BaseResponse<MineDouyinEntity>

}