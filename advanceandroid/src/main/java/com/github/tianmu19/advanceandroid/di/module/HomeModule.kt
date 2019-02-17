package com.github.tianmu19.advanceandroid.di.module

import com.github.tianmu19.advanceandroid.mvp.contract.HomeContract
import com.github.tianmu19.advanceandroid.mvp.model.HomeModel
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DryCargoFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.GirlFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MineFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.WanAndroidFragment
import com.jess.arms.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 21:42
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class HomeModule(private val view: HomeContract.View) {
    @ActivityScope
    @Provides
    fun provideHomeView(): HomeContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideHomeModel(model: HomeModel): HomeContract.Model {
        return model
    }
    @ActivityScope
    @Provides
    internal fun provideDryCagoFragment()= DryCargoFragment()

    @ActivityScope
    @Provides
    internal fun provideGirlFragment()= GirlFragment()

    @ActivityScope
    @Provides
    internal fun provideWanAndroidFragment()= WanAndroidFragment()

    @ActivityScope
    @Provides
    internal fun provideMineFragment()= MineFragment()


}
