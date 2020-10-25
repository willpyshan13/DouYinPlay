package com.will.play.mine.entity

data class MineTalentRecordEntity(
        val counter: Int,
        val darenInfo: MineDarenInfoEntity,
        val dataLists: List<MineTalentRecordDataListsEntity>,
        val total: Int
)