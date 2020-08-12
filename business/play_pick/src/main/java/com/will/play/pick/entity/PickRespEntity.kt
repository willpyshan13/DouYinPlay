package com.will.play.pick.entity

/**
 * Desc:
 *
 * Date: 2020-08-12
 * Copyright: Copyright (c) 2018-2020
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class PickGoodTypeEntity : ArrayList<PickGoodTypeEntityItem>()

data class PickGoodTypeEntityItem(
    val area_id: Int,
    val count_user: Int,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val group_id: Int,
    val group_name: Any,
    val headimg: String,
    val headimg_text: String,
    val id: Int,
    val name: String,
    val percent: Int,
    val percent_text: String,
    val sort_index: String,
    val status: Int,
    val status_name: String,
    val system_user_id: Int,
    val tb_id: String,
    val time_apply: Int,
    val time_create: Int,
    val time_create_text: String,
    val time_edit: Int,
    val time_edit_text: String,
    val url: String
)