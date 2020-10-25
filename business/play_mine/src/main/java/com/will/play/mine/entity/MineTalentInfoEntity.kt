package com.will.play.mine.entity

data class MineTalentInfoEntity(
        val dataInfo: MineTalentDataInfoEntity,
        val visitLists: List<String>,
        val visit_count: Int
)