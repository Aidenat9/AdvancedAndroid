package com.github.tianmu19.advanceandroid.mvp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.di.component.DaggerDryCargoComponent
import com.github.tianmu19.advanceandroid.di.module.DryCargoModule
import com.github.tianmu19.advanceandroid.mvp.contract.DryCargoContract
import com.github.tianmu19.advanceandroid.mvp.presenter.DryCargoPresenter
import com.github.tianmu19.advanceandroid.mvp.ui.LazyLoadFragment
import com.github.tianmu19.advanceandroid.mvp.ui.activity.EasyWebActivity
import com.github.tianmu19.advanceandroid.mvp.ui.adapter.DryCargoAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.fragment_dry_cargo.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject


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

class DryCargoFragment : LazyLoadFragment<DryCargoPresenter>(), DryCargoContract.View,
    SwipeRefreshLayout.OnRefreshListener {
    override fun lazyLoad() {
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.post {
            mSwipeRefreshLayout.isRefreshing = true
            onRefresh()
        }
    }

    override fun onRefresh() {
        mPresenter?.getGankio(mType, true)
    }

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager
    @Inject
    lateinit var mAdapter: DryCargoAdapter

    var mType: String = "Android"

    override fun getContext(): Context {
        return super.getContext()!!
    }

    override fun startLoadMore() {
    }

    override fun endLoadMore() {
    }

    companion object {
        fun newInstance(): DryCargoFragment {
            val fragment = DryCargoFragment()
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerDryCargoComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .dryCargoModule(DryCargoModule(this))
            .build()
            .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_dry_cargo, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rv_drycargo.layoutManager = mLayoutManager
        rv_drycargo.adapter = mAdapter.apply {
            setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                    activity?.startActivity<EasyWebActivity>(
                        "title" to datas[position].desc,
                        "url" to datas[position].url
                    )
                }

            })
            setEnableLoadMore(true)
            openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM)
            setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    mPresenter?.getGankio(mType, false)
                }

            }, rv_drycargo)
        }

    }

    override fun showLoading() {

    }

    override fun hideLoading() {
        mSwipeRefreshLayout.isRefreshing = false
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
