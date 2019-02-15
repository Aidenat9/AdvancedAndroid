package com.github.tianmu19.advanceandroid.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationBar.BACKGROUND_STYLE_RIPPLE
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.di.component.DaggerHomeComponent
import com.github.tianmu19.advanceandroid.di.module.HomeModule
import com.github.tianmu19.advanceandroid.mvp.contract.HomeContract
import com.github.tianmu19.advanceandroid.mvp.presenter.HomePresenter
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.ArticleFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.DrycargoFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MeizhiFragment
import com.github.tianmu19.advanceandroid.mvp.ui.fragment.MineFragment
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


/**
 * ================================================
 * Description:主页
 * ================================================
 */

class HomeActivity : BaseActivity<HomePresenter>(), HomeContract.View, BottomNavigationBar.OnTabSelectedListener {



    @Inject
    lateinit var drycargoFragment: DrycargoFragment
    @Inject
    lateinit var articleFragment : ArticleFragment
    @Inject
    lateinit var meizhiFragment: MeizhiFragment
    @Inject
    lateinit var mineFragment: MineFragment



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
        setupPages()
    }

    private fun setupPages() {
        mBNavBar.setBackgroundStyle(BACKGROUND_STYLE_RIPPLE)
        mBNavBar.addItem(BottomNavigationItem(R.drawable.tab_article_active,"gankio")
            .setActiveColorResource(android.R.color.black)
            .setInActiveColorResource(R.color.colorTabInActive))
            .addItem(BottomNavigationItem(R.drawable.tab_article_active,"gankio"))
            .addItem(BottomNavigationItem(R.drawable.tab_article_active,"gankio"))
            .addItem(BottomNavigationItem(R.drawable.tab_article_active,"gankio")).setFirstSelectedPosition(0)
            .initialise()
        mBNavBar.setTabSelectedListener(this)
        onTabSelected(0)
    }


    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> {
                switchFragment(mHomeFragment)
            }
            1 -> {
                switchFragment(mCategoryFragment)
            }
            2 -> {
                switchFragment(mMineFragment)
            }
            else -> {
            }
        }
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabReselected(position: Int) {

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
}
