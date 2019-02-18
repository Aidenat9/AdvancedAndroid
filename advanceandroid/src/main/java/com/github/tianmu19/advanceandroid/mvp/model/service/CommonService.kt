package com.github.tianmu19.advanceandroid.mvp.model.service

import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.GankIoDataBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.CollectUrlBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.LoginBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.NaviJsonBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.TreeBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.wanandroid.listbean.WanListBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/15 21:01
 *包名：com.github.tianmu19.advanceandroid.mvp.model.service.cache
 *<p>description:   接口服务         </p>
 */
interface CommonService {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * 请求个数： 数字，大于0
     * 第几页：数字，大于0
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
     fun getGankIoData(@Path("type") id: String, @Path("page") page: Int, @Path("pre_page") pre_page: Int): Observable<GankIoDataBean>


    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
     fun getHomeList(@Path("page") page: Int, @Query("cid") cid: Int?): Observable<WanListBean>

    /**
     * 玩安卓登录
     *
     * @param username 用户名
     * @param password 密码
     */
    @FormUrlEncoded
    @POST("user/login")
    abstract fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginBean>

    /**
     * 玩安卓注册
     */
    @FormUrlEncoded
    @POST("user/register")
    abstract fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String): Observable<LoginBean>

    /**
     * 退出
     */
    @GET("user/logout/json")
    abstract fun logout(): Observable<LoginBean>

    /**
     * 收藏文章列表
     *
     * @param page 页码
     */
    @GET("lg/collect/list/{page}/json")
    abstract fun getCollectList(@Path("page") page: Int): Observable<WanListBean>

    /**
     * 收藏本站文章，errorCode返回0为成功
     *
     * @param id 文章id
     */
    @POST("lg/collect/{id}/json")
    abstract fun collect(@Path("id") id: Int): Observable<WanListBean>

    /**
     * 取消收藏(首页文章列表)
     *
     * @param id 文章id
     */
    @POST("lg/uncollect_originId/{id}/json")
    abstract fun unCollectOrigin(@Path("id") id: Int): Observable<WanListBean>

    /**
     * 取消收藏，我的收藏页面（该页面包含自己录入的内容）
     *
     * @param id       文章id
     * @param originId 列表页下发，无则为-1
     * (代表的是你收藏之前的那篇文章本身的id；
     * 但是收藏支持主动添加，这种情况下，没有originId则为-1)
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    abstract fun unCollect(@Path("id") id: Int, @Field("originId") originId: Int): Observable<WanListBean>

    /**
     * 体系数据
     */
    @GET("tree/json")
    abstract fun getTree(): Observable<TreeBean>

    /**
     * 收藏网址
     *
     * @param name 标题
     * @param link 链接
     */
    @FormUrlEncoded
    @POST("lg/collect/addtool/json")
    abstract fun collectUrl(@Field("name") name: String, @Field("link") link: String): Observable<WanListBean>

    /**
     * 编辑收藏网站
     *
     * @param name 标题
     * @param link 链接
     */
    @FormUrlEncoded
    @POST("lg/collect/updatetool/json")
    abstract fun updateUrl(@Field("id") id: Int, @Field("name") name: String, @Field("link") link: String): Observable<WanListBean>

    /**
     * 删除收藏网站
     *
     * @param id 收藏网址id
     */
    @FormUrlEncoded
    @POST("lg/collect/deletetool/json")
    abstract fun unCollectUrl(@Field("id") id: Int): Observable<WanListBean>


    /**
     * 收藏网站列表
     */
    @GET("lg/collect/usertools/json")
    abstract fun getCollectUrlList(): Observable<CollectUrlBean>

    /**
     * 导航数据
     */
    @GET("navi/json")
    abstract fun getNaviJson(): Observable<NaviJsonBean>


}