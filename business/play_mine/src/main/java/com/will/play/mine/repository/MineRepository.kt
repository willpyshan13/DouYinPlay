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

    suspend fun getUpgrade() = mineService.getUpgrade("0").check()

    /**
     * 获取订单详情
     */
    suspend fun getOrder2Add(upgrade_id:String,is_group:String="0") = mineService.getOrder2Add("0",upgrade_id,is_group).check()

    /**
     * 获取支付信息
     */
    suspend fun getOrder2Pay(id:String) = mineService.getOrder2Pay(id).check()

    /**
     * 积分记录
     */
    suspend fun getPointLog(id:String="41") = mineService.getPointLog(id).check()

    /**
     * 提现检测
     */
    suspend fun getPointApply() = mineService.getPointApply().check()

    /**
     * 提现检测
     */
    suspend fun getPointApplyAdd(money:String?) = mineService.getPointApplyAdd(money).check()


    /**
     * 提现记录
     */
    suspend fun getPointApplyIndex(page:Int) = mineService.getPointApplyIndex(page).check()

    /**
     * 修改个人信息
     */
    suspend fun getUserEdit(headUrl:String,nickName:String,gender:String,area:String,qq:String,wechat:String) = mineService.getUseredit(headUrl,nickName,gender,area,qq,wechat).check()
}