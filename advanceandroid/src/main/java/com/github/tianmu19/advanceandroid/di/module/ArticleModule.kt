package com.github.tianmu19.advanceandroid.di.module

import com.github.tianmu19.advanceandroid.mvp.contract.ArticleContract
import com.github.tianmu19.advanceandroid.mvp.model.ArticleModel
import com.jess.arms.di.scope.FragmentScope
import dagger.Module
import dagger.Provides


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 14:47
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
//构建ArticleModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class ArticleModule(private val view: ArticleContract.View) {
    @FragmentScope
    @Provides
    fun provideArticleView(): ArticleContract.View {
        return this.view
    }

    @FragmentScope
    @Provides
    fun provideArticleModel(model: ArticleModel): ArticleContract.Model {
        return model
    }
}
