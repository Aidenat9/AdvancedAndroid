package com.github.tianmu19.advanceandroid.mvp.model.service

import com.github.tianmu19.advanceandroid.mvp.model.entity.drycargo.GankIoDataBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.article.WanAndroidListBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.navi.NaviJsonBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.treedata.TreeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/15 16:19
 *包名：com.github.tianmu19.advanceandroid.mvp.model.service
 *<p>description:   接口服务 (基本地址不同，记得切换)        </p>
 */
interface CommonService {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/Android/{pre_page}/{page}")
    fun getGankIoData(@Path("page") page: Int, @Path("pre_page") pre_page: Int): Observable<GankIoDataBean>

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int, @Query("cid") cid: Int?): Observable<WanAndroidListBean>

    /**
     * 体系数据
     */
    @GET("tree/json")
    fun getTree(): Observable<TreeBean>

    /**
     * 导航数据
     */
    @GET("navi/json")
    fun getNaviJson(): Observable<NaviJsonBean>


}