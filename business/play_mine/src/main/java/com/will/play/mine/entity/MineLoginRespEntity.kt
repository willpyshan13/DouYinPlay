package com.will.play.mine.entity

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

data class MineDouyinEntity(
    val Token: String,
    val code: String,
    val douyinUserInfo: DouyinUserInfo,
    val state: String
)

data class PostData(
    val PHPSESSID: String,
    val code: String,
    val dk_login_auto_user: String,
    val dk_token: String
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

data class UserInfo(
    val andriod_openid: String,
    val area_id: String,
    val avatar: String,
    val city: String,
    val county: String,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val douyin_openid: String,
    val douyin_user_id: String,
    val douyin_user_nickname: Any,
    val douyin_user_open_id: Any,
    val download_day_remain: Int,
    val download_day_total: Int,
    val download_total: Int,
    val download_day_use: Int,
    val download_month_remain: Int,
    val download_month_total: Int,
    val download_month_use: Int,
    val download_point_check: Int,
    val download_point_total: Int,
    val employeeInfo: Any,
    val employee_id: Int,
    val employee_name: String,
    val employee_qrcode_text: String,
    val group_id: Int,
    val group_name: String,
    val headimgurl: String,
    val id: Int,
    val ios_openid: String,
    val lat: String,
    val lng: String,
    val mobile: String,
    val month_download_times: String,
    val nickname: String,
    val openid: String,
    val password: String,
    val point: String,
    val province: String,
    val qq: String,
    val roleInfo: RoleInfo,
    val role_id: Int,
    val role_name: String,
    val sex_id: String,
    val status: Int,
    val status_name: String,
    val system_role_name: String,
    val system_user_disable: String,
    val system_user_id: Int,
    val taobao_user_id: String,
    val taobao_user_nick: String,
    val taobao_user_no: String,
    val tb_id: String,
    val time_apply: String,
    val time_create: Int,
    val time_create_text: String,
    val time_download: String,
    val time_edit: Int,
    val time_edit_text: String,
    val time_expire: Int,
    val time_expire_text: String,
    val time_login: String,
    val time_start: Int,
    val time_start_text: String,
    val type_id: String,
    val unionid: String,
    val user_id: String,
    val username: String,
    val web_openid: String,
    val wechat_no: String
)

data class RoleInfo(
    val download_day: Int,
    val download_month: Int
)