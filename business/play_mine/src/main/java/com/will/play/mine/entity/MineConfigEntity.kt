package com.will.play.mine.entity

data class MineConfigEntity(
    val authLists: List<AuthLists>,
    val fansLists: List<FansLists>,
    val levelLists: List<LevelLists>,
    val platformLists: List<PlatformLists>,
    val reasonLists: List<ReasonLists>,
    val sexLists: List<SexLists>,
    val typeLists: List<TypeLists>
)