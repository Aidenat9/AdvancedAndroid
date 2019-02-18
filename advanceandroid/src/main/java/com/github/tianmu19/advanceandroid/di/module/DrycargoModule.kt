package com.github.tianmu19.advanceandroid.di.module

import android.support.v7.widget.LinearLayoutManager
import com.github.tianmu19.advanceandroid.mvp.contract.DryCargoContract
import com.github.tianmu19.advanceandroid.mvp.model.DryCargoModel
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.Result
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.DryCargoAdapter
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides


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
@Module
//构建DryCargoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class DryCargoModule(private val view: DryCargoContract.View) {
    @FragmentScope
    @Provides
    fun provideDryCargoView(): DryCargoContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideDryCargoModel(model: DryCargoModel): DryCargoContract.Model {
        return model
    }

    //引入数据和adapter
    @FragmentScope
    @Provides
    internal fun provideLayoutManager() = LinearLayoutManager(view.getContext())

    @FragmentScope
    @Provides
    internal fun provideDatas()= mutableListOf<Result>()

    @FragmentScope
    @Provides
    internal fun provideAdapter(datas:MutableList<Result>)= DryCargoAdapter(datas)




}
