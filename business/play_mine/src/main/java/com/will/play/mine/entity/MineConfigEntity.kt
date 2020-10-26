package com.will.play.mine.entity

data class MineConfigEntity(
    val authLists: List<FansLists>,
    val fansLists: List<FansLists>,
    val levelLists: List<FansLists>,
    val platformLists: List<FansLists>,
    val reasonLists: List<FansLists>,
    val sexLists: List<FansLists>,
    val typeLists: List<TypeLists>
)