package com.will.play.third.repository

import com.will.habit.base.BaseModel
import com.will.habit.http.RetrofitClient
import com.will.play.third.service.ThirdService

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
class ThirdRepository : BaseModel<Any>() {
    private val mineService by lazy { RetrofitClient.instance.create(ThirdService::class.java) }
}