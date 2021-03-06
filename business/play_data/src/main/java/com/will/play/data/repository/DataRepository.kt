package com.will.play.data.repository

import com.will.habit.base.BaseModel
import com.will.habit.extection.check
import com.will.habit.http.RetrofitClient
import com.will.play.data.service.DataService

/**
 * Desc:
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class DataRepository : BaseModel<Any>() {
    private val homeService by lazy { RetrofitClient.instance.create(DataService::class.java) }

    suspend fun  getHomeBanner()=homeService.getHomeBanner(2).check();

    suspend fun  getTaskRecommend()=homeService.getTaskRecommend().check();

    /**
     * 抖音授权
     */
    suspend fun douyinAuth(username:String?) = homeService.getDouyinoauthCallback(username).check()

    suspend fun  getDouyinVideoIndex(videoId:String="0")=homeService.getDouyinVideoIndex(videoId).check()


    suspend fun  getTaobaoOrderIndex()=homeService.getTaobaoIndex().check()
}