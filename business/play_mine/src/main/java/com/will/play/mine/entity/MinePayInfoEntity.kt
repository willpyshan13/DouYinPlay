package com.will.play.mine.entity

data class MinePayInfoEntity(
        val orderInfo: PayOrderInfo,
        val payInfo: PayInfo,
        val pay_type: String
)

data class PayOrderInfo(
    val amount: String,
    val date_create: String,
    val disable: Int,
    val disable_name: String,
    val disable_text: String,
    val group_id: Int,
    val group_name: String,
    val id: Int,
    val incre_amount: String,
    val incre_time: String,
    val is_calc: String,
    val memo: String,
    val no: String,
    val note: String,
    val pay_amount: String,
    val pay_lock: String,
    val pay_type: String,
    val role_name: String,
    val serial_number: String,
    val serial_time: String,
    val share_order_id: String,
    val status: Int,
    val status_name: String,
    val system_role_name: String,
    val system_user_disable: String,
    val system_user_id: Int,
    val tb_id: String,
    val time_calc: String,
    val time_close: String,
    val time_create: Int,
    val time_create_text: String,
    val time_edit: Int,
    val time_login: Int,
    val time_login_text: String,
    val time_pay: String,
    val time_pay_lock: String,
    val time_wx_pay: String,
    val time_wx_pay_raw: String,
    val to_role_id: String,
    val transaction_id: Any,
    val upgrade_id: String,
    val upgrade_name: String,
    val user_id: Int,
    val user_mobile: String,
    val user_nickname: String,
    val wait_amount: String
)

data class PayInfo(
    val appid: String,
    val noncestr: String,
    val `package`: String,
    val partnerid: String,
    val prepayid: String,
    val sign: String,
    val timestamp: Int
)