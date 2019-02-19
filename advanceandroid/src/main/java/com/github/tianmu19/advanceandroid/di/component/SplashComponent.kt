package com.github.tianmu19.advanceandroid.di.component

import com.github.tianmu19.advanceandroid.di.module.SplashModule
import com.github.tianmu19.advanceandroid.mvp.ui.activity.SplashActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope
import dagger.Component


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
@Component(modules = arrayOf(SplashModule::class), dependencies = arrayOf(AppComponent::class))
interface SplashComponent {
    fun inject(activity: SplashActivity)
}
