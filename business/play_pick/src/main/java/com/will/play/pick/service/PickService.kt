package com.will.play.pick.service

import com.will.habit.http.BaseResponse
import com.will.play.base.BannerEntity
import com.will.play.pick.entity.PickGoodTypeEntity
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
interface PickService {
    @GET("api.php/Index/ad")
    suspend fun getHomeBanner(@Query("group_id")pageNum:Int): BaseResponse<BannerEntity>

    @GET("api.php/GoodsType/index")
    suspend fun getGoodsType(@Query("pagesize")pageNum:Int): BaseResponse<PickGoodTypeEntity>
}