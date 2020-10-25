package com.will.play.home.service

import com.will.habit.http.BaseResponse
import com.will.play.base.entity.BannerEntity
import com.will.play.base.entity.HomeFilterEntity
import com.will.play.base.entity.RespUpdateEntity
import com.will.play.home.entity.HomeRespDataEntity
import com.will.play.home.entity.HomeRespListEntity
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
interface HomeService {
    @GET("api.php/Index/home")
    suspend fun getHomeIndex(): BaseResponse<HomeRespDataEntity>

    @GET("api.php/Index/ad")
    suspend fun getHomeBanner(@Query("group_id") pageNum: Int): BaseResponse<BannerEntity>

    @POST("api.php/User/android")
    suspend fun getUpdateInfo(@Query("VerChar") VerChar: String): BaseResponse<RespUpdateEntity>

    @POST("api.php/DarenApply/add")
    suspend fun getDarenApply(@Query("daren_id") daren_id: String): BaseResponse<RespUpdateEntity>

    @GET("api.php/FaAuthor/index")
    suspend fun getHomeFaAuthor(@Query("pageNum") pageNum: Int, @Query("keyWord") keyWord: String?, @Query("sort_id") sort_id: Int?, @Query("pageSize") pageSize: Int): BaseResponse<HomeRespListEntity>

    @GET("api.php/Daren/index")
    suspend fun getHomeDaren(@Query("pageNum") pageNum: Int,
                             @Query("daren_type_id") daren_type_id: Int?=0, @Query("level_id") level_id: Int=0,
                             @Query("sex_id") sex_id: Int?=0, @Query("auth_id") auth_id: Int=0,
                             @Query("sort_id") sort_id: Int?=0, @Query("pageSize") pageSize: Int=20): BaseResponse<HomeFilterEntity>
}