package com.will.play.pick.entity

/**
 * Desc: 商品详情页
 * @Author: zhuanghongzhan
 */
data class PickGoodDetailRespEntity(
        val copy_url: String,
        val taskInfo: TaskInfo
)

data class TaskInfo(
        val add_url: String,
        val area_id: Int,
        val back_point: String,
        val cat_leaf_name: String,
        val category_name: String,
        val count_video_remain: String,
        val coupon: String,
        val coupon_click_url: String,
        val coupon_info: String,
        val demo_video: String,
        val disable: Int,
        val disable_name: String,
        val disable_text: String,
        val dx_link: String,
        val feedback: Any,
        val feedbackLists: List<FeedBackEntity>?,
        val free_shipment: String,
        val goods_id: String,
        val goods_name: String,
        val goods_title: String,
        val goods_type_id: String,
        val group_id: Int,
        val group_name: String,
        val h_good_rate: String,
        val h_pay_rate30: String,
        val has_download: Int,
        val i_rfd_rate: String,
        val id: Int,
        val introduce: String,
        val is_back: String,
        val is_dou_plus: String,
        val is_dx: String,
        val is_feedback: String,
        val is_pay: String,
        val is_prepay: String,
        val item_id: String,
        val item_url: String,
        val ju_online_end_time: String,
        val ju_online_start_time: String,
        val ju_play_end_time: String,
        val ju_play_start_time: String,
        val ju_pre_show_end_time: String,
        val ju_pre_show_start_time: String,
        val kuadian_promotion_info: String,
        val liangdian: String,
        val material_lib_type: String,
        val max_commission: String,
        val max_commission_rate: String,
        val max_commission_rate_text: String,
        val mobile: String,
        val name: String,
        val nick: String,
        val num_iid: String,
        val per_point: String,
        val pict_url: String,
        val pict_url_text: String,
        val platform_id: Int,
        val platform_name: String,
        val play_info: String,
        val presale_deposit: String,
        val presale_discount_fee_text: String,
        val presale_end_time: String,
        val presale_start_time: String,
        val presale_tail_end_time: String,
        val presale_tail_start_time: String,
        val price: String,
        val price_text: String,
        val provcity: String,
        val quantity: String,
        val ratesum: String,
        val real_post_fee: String,
        val remain_download: Int,
        val reserve_price: String,
        val sale_price: String,
        val seller_id: String,
        val shop_dsr: String,
        val short_title: String,
        val sku_list: Any,
        val small_images: Any,
        val small_imagesLists: List<Any>,
        val desc_imagesLists: List<String>,
        val status: Int,
        val status_name: String,
        val status_text: String,
        val system_user_id: Int,
        val tb_id: String,
        val time_apply: Int,
        val time_apply_text: String,
        val time_create: Int,
        val time_create_text: String,
        val time_edit: Int,
        val time_end: String,
        val time_last: String,
        val title: String,
        val tmall_play_activity_end_time: String,
        val tmall_play_activity_start_time: String,
        val total_download: Int,
        val total_feedback: String,
        val total_point: String,
        val url: String,
        val user_avatar: String,
        val user_download: Int,
        val user_id: String,
        val user_type: String,
        val volume: String,
        val zk_final_price: String,
        val zk_final_price_text: String
)


data class FeedBackEntity(
        val feedPicList: List<Any>,
        val feedback: String,
        val feedbackDate: String,
        val readCount: String,
        val skuMap: HashMap<String, String>,
        val userNick: String
)