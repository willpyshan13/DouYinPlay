package com.will.play.third.repository

import com.will.habit.base.BaseModel
import com.will.habit.extection.check
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

    suspend fun submitDouyin(value1: String?, value2: String?, value3: String?,
                             value4: String?, value5: String?, value6: String?,
                             value7: String?, value8: String?, value9: String?,
                             value10: String?, value11: String?) =
            mineService.suimitDouyin(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11).check()

    suspend fun getUserIndex() = mineService.getUserIndex().check()

}