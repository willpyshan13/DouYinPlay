package com.will.play.mine.service

import com.will.habit.http.BaseResponse
import com.will.play.base.constant.Constants.BASE_API
import com.will.play.base.entity.HomeFilterEntity
import com.will.play.base.entity.RespUpdateEntity
import com.will.play.constant.WxInfo
import com.will.play.mine.entity.*
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

    @POST("$BASE_API/Daren/config")
    suspend fun getDarenConfig(): BaseResponse<MineConfigEntity>


    @POST("$BASE_API/PointApply/add")
    suspend fun getPointApplyAdd(@Query("amount") amount: String?): BaseResponse<MineRespRecordEntity>

    @POST("$BASE_API/Wechat/oauthCallback")
    suspend fun getWechAtoauthCallback(@Query("code") amount: String?): BaseResponse<MineRespRecordEntity>

    @POST("$BASE_API/Daren/detail")
    suspend fun getDarenDetail(@Query("id") id: String?): BaseResponse<MineTalentInfoEntity>

    @POST("$BASE_API/DarenFav/add")
    suspend fun getCollectDaren(@Query("daren_id") id: String?): BaseResponse<List<String>>


    @POST("$BASE_API/DarenFav/del")
    suspend fun getUnCollectDaren(@Query("daren_id") id: String?): BaseResponse<List<String>>

    @POST("api.php/DarenApply/add")
    suspend fun addDarenApply(@Query("daren_id") daren_id: String?): BaseResponse<RespUpdateEntity>

    @POST("$BASE_API/DarenOrder/index")
    suspend fun getDarenOrder(@Query("page") page: Int,@Query("pagesize") pagesize: Int=20,@Query("daren_id") daren_id: String?): BaseResponse<MineTalentRecordEntity>

    @POST("$BASE_API/DarenApply/index")
    suspend fun getDarenApply(@Query("page") page: Int,@Query("pagesize") pagesize: Int=20,@Query("status") status: String?): BaseResponse<HomeFilterEntity>

    @GET("api.php/Daren/index")
    suspend fun getHomeDaren(@Query("pageNum") pageNum: Int,
                             @Query("daren_type_id") daren_type_id: Int?=0, @Query("level_id") level_id: Int?=0,
                             @Query("sex_id") sex_id: Int?=0, @Query("auth_id") auth_id: Int?=0,@Query("platform_id") platform: Int?=0,
                             @Query("fans_id") fans: Int?=0,@Query("sort_id") sort_id: Int?=0, @Query("pageSize") pageSize: Int=2000): BaseResponse<HomeFilterEntity>


}
