package com.github.tianmu19.advanceandroid.app

import com.jess.arms.base.BaseApplication

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/18 17:08
 *包名：com.github.tianmu19.advanceandroid.app
 *<p>description:            </p>
 */
class MyApp: BaseApplication() {
    companion object {
        @JvmStatic lateinit var instance :MyApp
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}