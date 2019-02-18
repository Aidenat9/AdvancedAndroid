package com.github.tianmu19.advanceandroid.mvp.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/15 22:24
 *包名：com.github.tianmu19.advanceandroid.mvp.ui.adapter
 *<p>description:            </p>
 */
class HomePagerAdapter(private val mDatas: List<Fragment>, fragmentManager:FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        return mDatas[p0]
    }


    override fun getCount(): Int = mDatas.size


}