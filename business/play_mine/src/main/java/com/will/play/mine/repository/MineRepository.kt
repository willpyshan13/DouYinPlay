package com.will.play.mine.repository

import com.will.habit.base.BaseModel
import com.will.habit.extection.check
import com.will.habit.http.RetrofitClient
import com.will.play.mine.service.MineService

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
class MineRepository : BaseModel<Any>() {
    private val mineService by lazy { RetrofitClient.instance.create(MineService::class.java) }

    /**
     * 执行登陆
     */
    suspend fun getUserIndex() = mineService.getUserIndex().check()

}