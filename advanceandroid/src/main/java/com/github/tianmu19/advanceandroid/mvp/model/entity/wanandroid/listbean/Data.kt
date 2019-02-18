package com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.listbean

data class Data(
    val curPage: Int,
    val datas: List<DataX>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)