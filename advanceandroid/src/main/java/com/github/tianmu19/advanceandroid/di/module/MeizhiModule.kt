package com.github.tianmu19.advanceandroid.di.module

import android.support.v7.widget.StaggeredGridLayoutManager
import com.github.tianmu19.advanceandroid.mvp.contract.MeizhiContract
import com.github.tianmu19.advanceandroid.mvp.model.MeizhiModel
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.Result
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.MeizhiAdapter
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides


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
@Module
//构建MeizhiModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class MeizhiModule(private val view: MeizhiContract.View) {
    @FragmentScope
    @Provides
    fun provideMeizhiView(): MeizhiContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideMeizhiModel(model: MeizhiModel): MeizhiContract.Model {
        return model
    }
    @FragmentScope
    @Provides
    internal fun provideLayoutManager() = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    @FragmentScope
    @Provides
    internal fun provideDatas() = mutableListOf<Result>()
    @FragmentScope
    @Provides
    internal fun provideAdapter(datas:MutableList<Result>) = MeizhiAdapter(datas)

}
