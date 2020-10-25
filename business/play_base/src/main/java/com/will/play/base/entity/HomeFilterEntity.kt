package com.will.play.base.entity

data class HomeFilterEntity(
        val counter: Int,
        val dataLists: List<HomeFilterDataList>,
        val total: Int
)