package com.will.play.pick.repository

import com.will.habit.base.BaseModel
import com.will.habit.extection.check
import com.will.habit.http.RetrofitClient
import com.will.play.pick.service.PickService

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
class PickRepository : BaseModel<Any>() {
    private val homeService by lazy { RetrofitClient.instance.create(PickService::class.java) }

    suspend fun  getHomeBanner()=homeService.getHomeBanner(3).check();

    suspend fun  getGoodsType()=homeService.getGoodsType(10).check();

    suspend fun  getTaskIndex(page:Int,typeId:String="0",userId:String?="0")=homeService.getTaskIndex(page,goods_type_id = typeId,user_id = userId).check();

    suspend fun getGoodDetail(goodId:String)=homeService.getGoodDetail(goodId).check()

    suspend fun  getTaskDownload(page:String)=homeService.getTaskDownload(page).check();

    suspend fun  getVideoIndex(page:Int,downType:Int)=homeService.getVideoIndex(page,download_status = downType).check();

    suspend fun  getVideoBind(videoId:String,douyinVideoId:String)=homeService.getVideoBind(videoId,douyinVideoId).check();

    suspend fun  getDouyinVideoIndex(videoId:String)=homeService.getDouyinVideoIndex(videoId).check();

    /**
     * 抖音授权
     */
    suspend fun douyinAuth(username:String?) = homeService.getDouyinoauthCallback(username).check()

    suspend fun  getTaskRecommend()=homeService.getTaskRecommend().check();

}