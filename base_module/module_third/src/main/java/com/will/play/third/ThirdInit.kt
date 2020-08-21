package com.will.play.third

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig

object ThirdInit {
    @JvmStatic
    fun initShare(){
        DouYinOpenApiFactory.init(DouYinOpenConfig("aws5gwkxtpqb7ny2"))
    }
}