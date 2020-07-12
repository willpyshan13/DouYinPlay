package com.will.play.mine.service

import com.will.habit.http.BaseResponse
import com.will.play.base.constant.Constants.BASE_API
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
    suspend fun login(@Query("username") username: String, @Query("password") password: String): BaseResponse<String>
}
