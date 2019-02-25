package com.github.tianmu19.advanceandroid.mvp.ui

import android.os.Bundle
import com.jaeger.library.StatusBarUtil
import com.jess.arms.base.BaseActivity
import com.jess.arms.mvp.IPresenter

abstract class ABaseActivity<P : IPresenter> : BaseActivity<P>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.setTranslucent(this)
        super.onCreate(savedInstanceState)
    }
}