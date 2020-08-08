package com.will.play.home.entity

data class HomeRespDataEntity(
    val author_count_today: String,
    val flush_date: String,
    val order_count: String,
    val order_count_today: String,
    val order_pay_today: String,
    val order_rate_today: String
)

data class HomeRespListEntity(
    val dataLists: List<DataLists>
)

data class DataLists(
    val account_type: String,
    val addtime: Int,
    val addtime_text: String,
    val age: String,
    val area: String,
    val author_id: String,
    val avatar: String,
    val aweme_count: Int,
    val aweme_count_text: Int,
    val code: String,
    val digg_incr: Int,
    val digg_incr_text: Any,
    val disable: Int,
    val disable_name: String,
    val follow: Int,
    val follow_text: Int,
    val follower_count: Int,
    val follower_count_text: String,
    val id: Int,
    val label: String,
    val mark: String,
    val nickname: String,
    val product_count: Int,
    val product_count_text: Int,
    val remark: Any,
    val sex: Int,
    val sex_name: String,
    val sort_index: String,
    val tb_id: String,
    val time_create: Int,
    val time_edit: Int,
    val total_favorited: String,
    val total_favorited_text: String,
    val type: Int,
    val unique_id: String
)