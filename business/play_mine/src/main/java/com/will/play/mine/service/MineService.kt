package com.will.play.mine.service

import com.will.habit.http.BaseResponse
import com.will.play.base.constant.Constants.BASE_API
import com.will.play.constant.WxInfo
import com.will.play.mine.entity.*
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
    suspend fun getUpgrade(@Query("share_order_id") share_order_id: String?): BaseResponse<MineVipDetailEntity>

    @POST("$BASE_API/Order2/pay")
    suspend fun getOrder2Pay(@Query("id") id: String?): BaseResponse<MinePayInfoEntity>


    @POST("$BASE_API/Order2/add")
    suspend fun getOrder2Add(@Query("share_order_id") share_order_id: String?,@Query("upgrade_id") upgrade_id: String?,@Query("is_group") is_group: String?): BaseResponse<MineVipUpgradeEntity>

    @POST("$BASE_API/User/edit")
    suspend fun getUseredit(@Query("avatar") avatar: String?,@Query("nickname") nickname: String?,
                            @Query("sex_id") sex_id: String?,@Query("area_id") area_id: String?,
                            @Query("qq") qq: String?,@Query("wechat_no") wechat_no: String?): BaseResponse<MineVipUpgradeEntity>

    @POST("$BASE_API/PointLog/index")
    suspend fun getPointLog(@Query("type_id") share_order_id: String?): BaseResponse<MineRespRecordEntity>

    @POST("$BASE_API/PointApply/index")
    suspend fun getPointApplyIndex(@Query("page") page: Int,
                                   @Query("date_from") dateFrom: String,
                                   @Query("date_to") dateTo: String,
                                   @Query("pagesize") pagesize: Int = 20,
                                   @Query("type_id") status: Int = 0): BaseResponse<RespWithDrawHistoryEntity>


    @POST("$BASE_API/PointApply/check")
    suspend fun getPointApply(): BaseResponse<MineRespRecordEntity>

    @POST("$BASE_API/PointApply/add")
    suspend fun getPointApplyAdd(@Query("amount") amount: String?): BaseResponse<MineRespRecordEntity>

    @POST("$BASE_API/Wechat/oauthCallback")
    suspend fun getWechAtoauthCallback(@Query("code") amount: String?): BaseResponse<MineRespRecordEntity>
}
