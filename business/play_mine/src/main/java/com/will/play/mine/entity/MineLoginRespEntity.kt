package com.will.play.mine.entity

import com.will.play.base.entity.UserInfo

/**
 * Desc:登陆实体类
 *
 * Date: 2020-08-13
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
data class MineLoginMobileCodeEntity(
    val Token: String,
    val code: String
)


data class PostData(
    val PHPSESSID: String,
    val code: String,
    val dk_login_auto_user: String,
    val dk_token: String
)

data class DataX(
    val access_token: String,
    val captcha: String,
    val desc_url: String,
    val description: String,
    val error_code: Int,
    val expires_in: Int,
    val open_id: String,
    val refresh_expires_in: Int,
    val refresh_token: String,
    val scope: String
)

data class DataXX(
    val avatar: String,
    val avatar_larger: String,
    val captcha: String,
    val city: String,
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
    val union_id: String
)

data class MineUserInfo(
    val Token: String,
    val userInfo: UserInfo
)

data class MineUserInfoEntity(
    val Token: String,
    val douyinUserInfo: List<Any>,
    val taobaoUserInfo: TaobaoUserInfo,
    val userInfo: UserInfo,
    val wechatUserInfo: List<Any>
)

data class TaobaoUserInfo(
    val access_token: String,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val expire_time: String,
    val gender: Int,
    val gender_name: String,
    val id: Int,
    val r1_valid: String,
    val r2_valid: String,
    val refresh_token: String,
    val role_name: String,
    val system_role_name: String,
    val system_user_disable: String,
    val system_user_id: Int,
    val taobao_user_id: String,
    val taobao_user_nick: String,
    val tb_id: String,
    val time_create: Int,
    val time_create_text: String,
    val time_edit: Int,
    val time_edit_text: String,
    val time_expire: String,
    val time_login: Int,
    val time_login_text: String,
    val time_taobao_order: String,
    val token_type: String,
    val user_id: Int,
    val user_mobile: String,
    val user_nickname: String,
    val w1_valid: String,
    val w2_valid: String
)

