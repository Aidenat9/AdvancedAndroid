package com.github.tianmu19.advanceandroid.di.component

import com.github.tianmu19.advanceandroid.di.module.DryCargoModule
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DryCargoFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.FragmentScope
import dagger.Component


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
@FragmentScope
@Component(modules = arrayOf(DryCargoModule::class), dependencies = arrayOf(AppComponent::class))
interface DryCargoComponent {
    fun inject(fragment: DryCargoFragment)
}
