package com.will.play.base

/**
 * Desc:
 *
 * Date: 2020-08-12
 * Copyright: Copyright (c) 2018-2020
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
data class BannerEntity(
        val group_id: Int,
        val swiperLists: List<SwiperLists>
)

data class SwiperLists(
        val id: Int,
        val img: String,
        val name: String,
        val url: String
)