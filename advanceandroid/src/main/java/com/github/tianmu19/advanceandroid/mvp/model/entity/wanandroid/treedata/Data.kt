package com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.treedata

data class Data(
    val children: List<Children>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)