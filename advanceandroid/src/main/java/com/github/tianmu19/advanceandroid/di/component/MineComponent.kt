package com.github.tianmu19.advanceandroid.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.github.tianmu19.advanceandroid.di.module.MineModule

import com.jess.arms.di.scope.FragmentScope
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MineFragment


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
@FragmentScope
@Component(modules = arrayOf(MineModule::class), dependencies = arrayOf(AppComponent::class))
interface MineComponent {
    fun inject(fragment: MineFragment)
}
