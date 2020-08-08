package com.will.play.home.service

import com.will.habit.http.BaseResponse
import com.will.play.home.entity.HomeRespDataEntity
import retrofit2.http.GET

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
    @GET("/Index/home")
    suspend fun getHomeIndex():BaseResponse<HomeRespDataEntity>
}