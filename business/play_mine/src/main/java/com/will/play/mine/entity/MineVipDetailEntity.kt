package com.will.play.mine.entity

data class MineVipDetailEntity(
    val is_first_pay: Boolean,
    val is_visit_pay: Boolean,
    val lists: List<Any>,
    val role_id: Any,
    val upgradeLists: List<UpgradeLists>
)

data class UpgradeLists(
    val can_share: String,
    val content: String,
    val content_text: String,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val from_role_id: String,
    val from_role_lists: List<Int>,
    val id: Int,
    val incre_time: String,
    val name: String,
    val price: String,
    val price_default: String,
    val price_first: String,
    val price_share: String,
    val price_visit: String,
    val sort_index: String,
    val system_role_name: String,
    val tag: String,
    val tb_id: String,
    val time_create: Int,
    val time_create_text: String,
    val time_edit: Int,
    val time_login: Int,
    val time_login_text: String,
    val to_role_id: String,
    val user_id: Int
)