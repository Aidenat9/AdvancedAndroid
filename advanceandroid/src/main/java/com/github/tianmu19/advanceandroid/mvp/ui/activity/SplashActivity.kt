package com.github.tianmu19.advanceandroid.mvp.ui.activity

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.di.component.DaggerSplashComponent
import com.github.tianmu19.advanceandroid.di.module.SplashModule
import com.github.tianmu19.advanceandroid.mvp.contract.SplashContract
import com.github.tianmu19.advanceandroid.mvp.presenter.SplashPresenter
import com.github.tianmu19.advanceandroid.mvp.ui.ABaseActivity
import com.github.tianmu19.advanceandroid.utils.NotchSupportUtil
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_splash.*


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
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class SplashActivity : ABaseActivity<SplashPresenter>(), SplashContract.View {
    override fun getContext(): Context {
        return this
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSplashComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {

        return R.layout.activity_splash //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @TargetApi(Build.VERSION_CODES.P)
    override fun initData(savedInstanceState: Bundle?) {
        if(NotchSupportUtil.hasNotch(getContext())){
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        mIvSplash.apply { setImageResource(R.drawable.bg_spalsh) }
        mPresenter?.delayOpenHome()
    }

    override fun onStart() {
        super.onStart()
//        blurLayout.startBlur()
    }

    override fun onStop() {
//        blurLayout.pauseBlur()
        super.onStop()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun useEventBus(): Boolean {
        return false
    }

    override fun useFragment(): Boolean {
        return false
    }
}
