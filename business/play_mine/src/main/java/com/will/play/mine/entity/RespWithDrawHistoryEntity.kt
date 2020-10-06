package com.will.play.mine.entity

data class RespWithDrawHistoryEntity(
    val counter: Int,
    val dataLists: List<DataLists>,
    val total: Int,
    val sum_amount:String
)

data class DataLists(
    val account: String,
    val amount: String,
    val date_create: String,
    val id: Int,
    val ip: String,
    val is_success: String,
    val no: String,
    val note: String,
    val param1: String,
    val param2: String,
    val partner_trade_no: String,
    val payment_no: String,
    val payment_time: String,
    val point: String,
    val reason: String,
    val serial_number: String,
    val serial_time: String,
    val status: Int,
    val status_name: String,
    val tb_id: String,
    val time_apply: String,
    val time_create: Int,
    val time_create_text: String,
    val time_fail: String,
    val user_id: Int,
    val user_mobile: String,
    val user_nickname: String
)