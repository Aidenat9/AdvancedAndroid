package com.github.tianmu19.advanceandroid.mvp.model

import android.app.Application
import com.github.tianmu19.advanceandroid.mvp.api.Api
import com.github.tianmu19.advanceandroid.mvp.contract.DryCargoContract
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.GankIoDataBean
import com.github.tianmu19.advanceandroid.mvp.model.service.CommonService
import com.github.tianmu19.advanceandroid.mvp.model.service.cache.CacheCommonService
import com.google.gson.Gson
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel
import io.reactivex.Observable
import io.rx_cache2.DynamicKeyGroup
import io.rx_cache2.Reply
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 21:46
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class DryCargoModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), DryCargoContract.Model {

    override fun getDryCargoData(type: String, page: Int): Observable<GankIoDataBean> {
        // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
        RetrofitUrlManager.getInstance().putDomain("gank", Api.API_GANKIO)
        return mRepositoryManager.obtainCacheService(CacheCommonService::class.java)
            .getGankioData(
                mRepositoryManager.obtainRetrofitService(CommonService::class.java)
                    .getGankIoData(type, page), DynamicKeyGroup(type, page)
            ).map { gankio: Reply<GankIoDataBean> -> gankio.data }
    }

    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun onDestroy() {
        super.onDestroy()
    }
}
