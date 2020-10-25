package com.will.play.home.repository

import com.blankj.utilcode.util.AppUtils
import com.will.habit.base.BaseModel
import com.will.habit.extection.check
import com.will.habit.http.RetrofitClient
import com.will.habit.utils.Utils
import com.will.play.base.utils.PackageUtils
import com.will.play.home.entity.HomeRespDataEntity
import com.will.play.home.service.HomeService

/**
 * Desc:首页数据仓库
 *
 * Date: 2020-06-22
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class HomeRepository : BaseModel<Any>() {
    private val homeService by lazy { RetrofitClient.instance.create(HomeService::class.java) }

    suspend fun  getHomeData()=homeService.getHomeIndex().check();

    suspend fun  getHomeBanner()=homeService.getHomeBanner(1).check();

    suspend fun  getUpdateInfo()=homeService.getUpdateInfo(AppUtils.getAppVersionName()).check();

    suspend fun  getHomeList(pageNum:Int)=homeService.getHomeDaren(pageNum).check();
}