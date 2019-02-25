package com.github.tianmu19.advanceandroid.mvp.presenter

import android.app.Application
import com.github.tianmu19.advanceandroid.mvp.contract.MeizhiContract
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.GankIoDataBean
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.Result
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.MeizhiAdapter
import com.jess.arms.di.scope.FragmentScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.utils.RxLifecycleUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 14:46
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@FragmentScope
class MeizhiPresenter
@Inject
constructor(model: MeizhiContract.Model, rootView: MeizhiContract.View) :
    BasePresenter<MeizhiContract.Model, MeizhiContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    @Inject
    lateinit var mAdapter :MeizhiAdapter
    @Inject
    lateinit var mDatas:MutableList<Result>
    /**
     * 页数是从 1开始的
     */
    private var mPageIndex = 1

    fun getMeizhi(category:String,pullToRefresh:Boolean){
        if (pullToRefresh) mPageIndex = 1
        mModel.getMeizhi(category,mPageIndex).subscribeOn(Schedulers.io())
            .doOnSubscribe{
                if(pullToRefresh){
                    mRootView.showLoading()
                }else{
                    mRootView.startLoadMore()
                }
            }.subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally{
                if (pullToRefresh)
                    mRootView.hideLoading()//隐藏下拉刷新的进度条
                else {
                    mRootView.endLoadMore()//隐藏上拉加载更多的进度条
                }
            }
            .doOnError{mAdapter.loadMoreEnd()}
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object :ErrorHandleSubscriber<GankIoDataBean>(mErrorHandler){
                override fun onNext(t: GankIoDataBean) {
                    mPageIndex++
                    if(pullToRefresh){
                        mDatas.clear()
                        mDatas.addAll(t.results)
                        mAdapter.notifyDataSetChanged()
                    }else{
                        mDatas.addAll(t.results)
                        mAdapter.notifyItemRangeChanged(mDatas.size - t.results.size, mDatas.size)
                        mAdapter.loadMoreComplete()
                    }
                }
                override fun onError(t: Throwable) {
                    super.onError(t)
                }

            })

    }



}
