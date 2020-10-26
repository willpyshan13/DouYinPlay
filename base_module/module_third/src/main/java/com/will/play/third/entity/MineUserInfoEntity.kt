package com.will.play.third.entity
data class MineUserInfoEntity(
    val Token: String,
    val douyinUserInfo: DouyinUserInfoEntity
)

data class DouyinUserInfoEntity(
    val access_token: String,
    val all_fans_num: String,
    val avatar: String,
    val city: String,
    val client_id: String,
    val count_douyin_digg_count: String,
    val count_douyin_video: String,
    val country: String,
    val day_expire: Int,
    val day_expire_text: String,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val e_account_role: String,
    val error: String,
    val gender: Int,
    val gender_name: String,
    val id: Int,
    val nickname: String,
    val note: String,
    val open_id: String,
    val province: String,
    val refresh_token: String,
    val role_name: String,
    val system_role_name: String,
    val system_user_disable: String,
    val system_user_id: Int,
    val tb_id: String,
    val time_create: Int,
    val time_create_text: String,
    val time_create_text3: String,
    val time_edit: Int,
    val time_edit_text: String,
    val time_expire: Int,
    val time_flush: String,
    val time_login: Int,
    val time_login_text: String,
    val time_refresh: String,
    val union_id: String,
    val user_id: Int,
    val user_mobile: String,
    val user_nickname: String
)