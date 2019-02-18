package com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.article

import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.Data

data class WanAndroidListBean(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
)