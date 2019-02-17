package com.github.tianmu19.advanceandroid.di.module

import com.jess.arms.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

import com.github.tianmu19.advanceandroid.mvp.contract.WanAndroidContract
import com.github.tianmu19.advanceandroid.mvp.model.WanAndroidModel


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 21:47
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建WanAndroidModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class WanAndroidModule(private val view: WanAndroidContract.View) {
    @FragmentScope
    @Provides
    fun provideWanAndroidView(): WanAndroidContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideWanAndroidModel(model: WanAndroidModel): WanAndroidContract.Model {
        return model
    }
}
