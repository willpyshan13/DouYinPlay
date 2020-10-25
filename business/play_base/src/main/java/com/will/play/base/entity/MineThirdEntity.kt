package com.will.play.base.entity

data class MineDouyinEntity(
        val Token: String,
        val code: String,
        val douyinUserInfo: DouyinUserInfo,
        val state: String
)

data class DouyinUserInfo(
        val access_token: String,
        val avatar: String,
        val avatar_larger: String,
        val captcha: String,
        val city: String,
        val client_id: String,
        val country: String,
        val desc_url: String,
        val description: String,
        val district: String,
        val e_account_role: String,
        val error_code: Int,
        val gender: Int,
        val nickname: String,
        val open_id: String,
        val province: String,
        val refresh_token: String,
        val time_login: Int,
        val union_id: String
)
