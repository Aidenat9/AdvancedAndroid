package com.github.tianmu19.advanceandroid.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.app.FragmentBackHelper
import com.github.tianmu19.advanceandroid.di.component.DaggerHomeComponent
import com.github.tianmu19.advanceandroid.di.module.HomeModule
import com.github.tianmu19.advanceandroid.mvp.contract.HomeContract
import com.github.tianmu19.advanceandroid.mvp.presenter.HomePresenter
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.HomePagerAdapter
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DryCargoFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.GirlFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MineFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.WanAndroidFragment
import com.jess.arms.base.BaseActivity
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

class HomeActivity : BaseActivity<HomePresenter>(), HomeContract.View , BottomNavigationBar.OnTabSelectedListener{
    @Inject
    lateinit var dryCargoFragment: DryCargoFragment
    @Inject
    lateinit var girlFragment: GirlFragment
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
        return R.layout.activity_home //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        initViewPager()
        setupPages()
    }

    private fun initViewPager() {
        fragments = listOf(dryCargoFragment as Fragment,girlFragment as Fragment,wanAndroidFragment as Fragment, mineFragment as Fragment)
        viewpager.adapter = HomePagerAdapter(fragments,supportFragmentManager)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                mBNavBar.selectTab(p0)
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
                BottomNavigationItem(R.drawable.tab_drycargo_inactive, "干货")
                    .setActiveColorResource(R.color.teal)
                    .setInActiveColorResource(R.color.white)
                    .setBadgeItem(TextBadgeItem().setText("new").setTextColor(Color.WHITE)
                        .setBackgroundColor(Color.RED))
            )
            .addItem(
                BottomNavigationItem(R.drawable.tab_girl_inactive, "妹纸")
                    .setActiveColorResource(R.color.orange)
                    .setInActiveColorResource(R.color.white)
            )
            .addItem(
                BottomNavigationItem(R.drawable.tab_wanandroid_inactive, "文章")
                    .setActiveColorResource(R.color.brown)
                    .setInActiveColorResource(R.color.white)
            )
            .addItem(
                BottomNavigationItem(R.drawable.tab_mine_inactive, "我的")
                    .setActiveColorResource(R.color.blue)
                    .setInActiveColorResource(R.color.white)
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
