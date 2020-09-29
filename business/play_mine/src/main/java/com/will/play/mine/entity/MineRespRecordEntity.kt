package com.will.play.mine.entity

data class MineRespRecordEntity(
    val count_type41: Int,
    val counter: Int,
    val dataLists: List<RecordDataLists>,
    val sum_type41: Int,
    val total: Int
)

data class RecordDataLists(
    val amount: Int,
    val amount_abs: Int,
    val amount_mark: String,
    val balance: String,
    val collect_id: String,
    val date_create: String,
    val download_id: String,
    val id: Int,
    val ip: String,
    val month: String,
    val month_text: String,
    val note: String,
    val param1: String,
    val param2: String,
    val reason: String,
    val remain: String,
    val system_user_nickname: String,
    val task_id: String,
    val task_user_nickname: String,
    val tb_id: String,
    val time_create: Int,
    val time_create_text: String,
    val time_create_text1: String,
    val type_content: String,
    val type_id: Int,
    val type_name: String,
    val type_text: String,
    val user_id: Int,
    val user_nickname: String,
    val username: String,
    val video_id: String
)