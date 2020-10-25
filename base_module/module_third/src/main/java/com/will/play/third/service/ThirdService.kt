package com.will.play.third.service

import com.will.habit.http.BaseResponse
import com.will.play.third.entity.DouyinBindInfoEntity
import com.will.play.third.entity.MineUserInfoEntity
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
interface ThirdService {
    @POST("api.php/User/login")
    suspend fun login(@Query("username") username: String?, @Query("password") password: String?): BaseResponse<DouyinBindInfoEntity>

    @POST("api.php/User/index")
    suspend fun getUserIndex(): BaseResponse<MineUserInfoEntity>

    @POST("api.php/Daren/add")
    suspend fun suimitDouyin(@Query("price1") price1: String?, @Query("price2") price2: String?,
                             @Query("price3") price3: String?, @Query("price4") price4: String?,
                            @Query("commission") commission: String?, @Query("commission_rate") commission_rate: String?,
                             @Query("daren_type_id") daren_type_id: String?, @Query("name") name: String?,
                             @Query("douyin_no") douyin_no: String?, @Query("wechat_no") wechat_no: String?, @Query("mobile") mobile: String?): BaseResponse<DouyinBindInfoEntity>
}
