package com.will.play.mine.service

import com.will.habit.http.BaseResponse
import com.will.play.base.constant.Constants.BASE_API
import com.will.play.mine.entity.MineLoginMobileCodeEntity
import com.will.play.mine.entity.MineUserInfo
import com.will.play.mine.entity.MineVipDetailEntity
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
interface MineService {
    @POST("$BASE_API/User/login")
    suspend fun login(@Query("username") username: String?, @Query("password") password: String?): BaseResponse<MineUserInfo>

    @POST("$BASE_API/User/checkVerifyCode")
    suspend fun checkVerifyCode(@Query("mobile") username: String?, @Query("code") password: String?): BaseResponse<MineLoginMobileCodeEntity>


    @POST("$BASE_API/User/getVerifyCode")
    suspend fun getVerifyCode(@Query("mobile") username: String?): BaseResponse<MineLoginMobileCodeEntity>


    @POST("$BASE_API/User/index")
    suspend fun getUserIndex(): BaseResponse<MineUserInfo>

    @POST("$BASE_API/Upgrade/index")
    suspend fun getUpgrade(@Query("share_order_id") username: String?): BaseResponse<MineVipDetailEntity>
}
