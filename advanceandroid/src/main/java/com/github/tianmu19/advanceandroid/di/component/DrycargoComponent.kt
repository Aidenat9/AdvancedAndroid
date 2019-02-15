package com.github.tianmu19.advanceandroid.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.github.tianmu19.advanceandroid.di.module.DrycargoModule

import com.jess.arms.di.scope.FragmentScope
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DrycargoFragment


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
@Component(modules = arrayOf(DrycargoModule::class), dependencies = arrayOf(AppComponent::class))
interface DrycargoComponent {
    fun inject(fragment: DrycargoFragment)
}
