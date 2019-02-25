package com.github.tianmu19.advanceandroid.mvp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.di.component.DaggerMeizhiComponent
import com.github.tianmu19.advanceandroid.di.module.MeizhiModule
import com.github.tianmu19.advanceandroid.mvp.contract.MeizhiContract
import com.github.tianmu19.advanceandroid.mvp.presenter.MeizhiPresenter
import com.github.tianmu19.advanceandroid.mvp.ui.LazyLoadFragment
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.MeizhiAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_meizhi.*
import javax.inject.Inject

class MeizhiFragment : LazyLoadFragment<MeizhiPresenter>(), MeizhiContract.View, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        mPresenter?.getMeizhi(mType, true)
    }

    @Inject
    lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    @Inject
    lateinit var mAdapter: MeizhiAdapter

    override fun lazyLoad() {
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            onRefresh()
        }
    }


    override fun getContext(): Context {
        return super.getContext()!!
    }

    override fun startLoadMore() {
    }

    override fun endLoadMore() {

    }

    companion object {
        fun newInstance(): MeizhiFragment {
            val fragment = MeizhiFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMeizhiComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .meizhiModule(MeizhiModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_meizhi, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    var mType: String = "福利"

    private fun initRecyclerView() {
        rv_meizhi.layoutManager = staggeredGridLayoutManager
        rv_meizhi.adapter = mAdapter.apply {

            setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//                    activity!!.startActivity<>(datas[position].url)
                }

            })
            setEnableLoadMore(true)
            openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
            setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    mPresenter?.getMeizhi(mType, false)
                }

            }, rv_meizhi)
        }
    }

    override fun setData(data: Any?) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {

    }
}
