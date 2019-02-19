package com.github.tianmu19.advanceandroid.mvp.presenter

import android.app.Application
import com.github.tianmu19.advanceandroid.mvp.contract.SplashContract
import com.github.tianmu19.advanceandroid.mvp.ui.activity.HomeActivity
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/19/2019 14:44
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
class SplashPresenter
@Inject
constructor(model: SplashContract.Model, rootView: SplashContract.View) :
    BasePresenter<SplashContract.Model, SplashContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy()
    }
    fun delayOpenHome(){
        Observable.timer(3, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mRootView.getContext().startActivity<HomeActivity>()
            mRootView.killMyself()}
    }
}
