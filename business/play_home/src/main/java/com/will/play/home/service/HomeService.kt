package com.will.play.home.service

import com.will.habit.http.BaseResponse
import com.will.play.base.BannerEntity
import com.will.play.home.entity.HomeRespDataEntity
import com.will.play.home.entity.HomeRespListEntity
import retrofit2.http.GET
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
interface HomeService {
    @GET("api.php/Index/home")
    suspend fun getHomeIndex():BaseResponse<HomeRespDataEntity>

    @GET("api.php/Index/ad")
    suspend fun getHomeBanner(@Query("group_id")pageNum:Int):BaseResponse<BannerEntity>


    @GET("api.php/FaAuthor/index")
    suspend fun getHomeFaAuthor(@Query("pageNum")pageNum:Int, @Query("keyWord")keyWord:String?, @Query("sort_id")sort_id:Int?, @Query("pageSize")pageSize:Int):BaseResponse<HomeRespListEntity>
}