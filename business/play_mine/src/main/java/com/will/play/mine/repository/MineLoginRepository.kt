package com.will.play.mine.repository

import com.will.habit.base.BaseModel
import com.will.habit.http.RetrofitClient
import com.will.play.mine.service.MineService

/**
 * Desc:登陆模块数据仓库
 *
 * Date: 2020-07-12
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class MineLoginRepository : BaseModel<Any>() {
    private val mineService by lazy { RetrofitClient.instance.create(MineService::class.java) }

    /**
     * 执行登陆
     */
    suspend fun login(username:String,password:String){
        mineService.login(username,password)
    }
}