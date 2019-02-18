package com.github.tianmu19.advanceandroid.mvp.model.service.cache

import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.GankIoDataBean
import io.reactivex.Observable
import io.rx_cache2.DynamicKeyGroup
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply
import java.util.concurrent.TimeUnit

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/18 10:36
 *包名：com.github.tianmu19.advanceandroid.mvp.model.service.cache
 *<p>description:            </p>
 */
interface CacheCommonService {
    companion object {
        const val CACHE_TIME = 1L
    }

    @LifeCache(duration = CACHE_TIME, timeUnit = TimeUnit.HOURS)
    fun getGankioData(gankio:Observable<GankIoDataBean>,keyGroup: DynamicKeyGroup):Observable<Reply<GankIoDataBean>>
}