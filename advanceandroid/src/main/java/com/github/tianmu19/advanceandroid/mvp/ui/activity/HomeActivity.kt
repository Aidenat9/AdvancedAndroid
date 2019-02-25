package com.github.tianmu19.advanceandroid.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.app.FragmentBackHelper
import com.github.tianmu19.advanceandroid.di.component.DaggerHomeComponent
import com.github.tianmu19.advanceandroid.di.module.HomeModule
import com.github.tianmu19.advanceandroid.mvp.contract.HomeContract
import com.github.tianmu19.advanceandroid.mvp.presenter.HomePresenter
import com.github.tianmu19.advanceandroid.mvp.ui.ABaseActivity
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.HomePagerAdapter
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DryCargoFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MeizhiFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MineFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.WanAndroidFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


/**
 * ================================================
 * Description:
 * ================================================
 */

class HomeActivity : ABaseActivity<HomePresenter>(), HomeContract.View , BottomNavigationBar.OnTabSelectedListener{
    @Inject
    lateinit var dryCargoFragment: DryCargoFragment
    @Inject
    lateinit var girlFragment: MeizhiFragment
    @Inject
    lateinit var wanAndroidFragment: WanAndroidFragment
    @Inject
    lateinit var mineFragment: MineFragment
    lateinit var fragments:List<Fragment>

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return com.github.tianmu19.advanceandroid.R.layout.activity_home //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {

        initViewPager()
        setupPages()
        getNotchParams()

    }


    fun getNotchParams() {
       if(Build.VERSION.SDK_INT >= 28){
           val decorView2 = window.decorView
           decorView2.post {
               val displayCutout = decorView2.rootWindowInsets.displayCutout
               Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.safeInsetLeft)
               Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.safeInsetRight)
               Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.safeInsetTop)
               Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.safeInsetBottom)

               val rects = displayCutout.boundingRects
               if (rects == null || rects.size == 0) {
                   Log.e("TAG", "不是刘海屏")
               } else {
                   Log.e("TAG", "刘海屏数量:" + rects.size)
                   for (rect in rects) {
                       Log.e("TAG", "刘海屏区域：$rect")
                   }
               }
           }
       }
    }

    private fun initViewPager() {
        fragments = listOf(dryCargoFragment as Fragment,girlFragment as Fragment,wanAndroidFragment as Fragment, mineFragment as Fragment)
        viewpager.adapter = HomePagerAdapter(fragments,supportFragmentManager)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                mBNavBar.selectTab(p0)
                when (p0){
                    0->ll_home.setBackgroundResource(R.drawable.bg_home_drycargo)
                    1->ll_home.setBackgroundResource(R.drawable.bg_home_girl)
                    2->ll_home.setBackgroundResource(R.drawable.bg_home_article)
                    3->ll_home.setBackgroundResource(R.drawable.bg_home_mine)
                }
            }

            override fun onPageScrollStateChanged(p0: Int) {
            }
        })
        //缓存4个页面，
        viewpager.offscreenPageLimit = 4

    }


    /**
     * 三个页面的初始化
     */
    private fun setupPages() {
        mBNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
        mBNavBar.setMode(BottomNavigationBar.MODE_SHIFTING)
        mBNavBar
            .addItem(
                BottomNavigationItem(com.github.tianmu19.advanceandroid.R.drawable.tab_drycargo_inactive, "干货")
                    .setActiveColorResource(com.github.tianmu19.advanceandroid.R.color.teal)
                    .setInActiveColorResource(com.github.tianmu19.advanceandroid.R.color.white)
                    .setBadgeItem(TextBadgeItem().setText("new").setTextColor(Color.WHITE)
                        .setBackgroundColor(Color.RED))
            )
            .addItem(
                BottomNavigationItem(com.github.tianmu19.advanceandroid.R.drawable.tab_girl_inactive, "妹纸")
                    .setActiveColorResource(com.github.tianmu19.advanceandroid.R.color.orange)
                    .setInActiveColorResource(com.github.tianmu19.advanceandroid.R.color.white)
            )
            .addItem(
                BottomNavigationItem(com.github.tianmu19.advanceandroid.R.drawable.tab_wanandroid_inactive, "文章")
                    .setActiveColorResource(com.github.tianmu19.advanceandroid.R.color.brown)
                    .setInActiveColorResource(com.github.tianmu19.advanceandroid.R.color.white)
            )
            .addItem(
                BottomNavigationItem(com.github.tianmu19.advanceandroid.R.drawable.tab_mine_inactive, "我的")
                    .setActiveColorResource(com.github.tianmu19.advanceandroid.R.color.blue)
                    .setInActiveColorResource(com.github.tianmu19.advanceandroid.R.color.white)
            )
            .setFirstSelectedPosition(0)
            .initialise()
        mBNavBar.setTabSelectedListener(this)
    }

    override fun onTabReselected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabSelected(position: Int) {
        viewpager.currentItem = position
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

    private var mExitTime = 0L
    override fun onBackPressed() {
        if (FragmentBackHelper.HandleBack(supportFragmentManager)) {
            return
        }

        if (System.currentTimeMillis() - 2000 < mExitTime) {
            super.onBackPressed()
        } else {
            Toasty.info(applicationContext, "再按一次退出").show()
            mExitTime = System.currentTimeMillis()
        }


    }
}
