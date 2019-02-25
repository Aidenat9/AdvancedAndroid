package com.github.tianmu19.advanceandroid.mvp.ui.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.app.loadImage
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.Result
import com.yangyan.xxp.yangyannew.app.YImageConfig
import jp.wasabeef.glide.transformations.BlurTransformation
import timber.log.Timber

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/25 14:56
 *包名：com.github.tianmu19.advanceandroid.mvp.ui.adapter
 *<p>description:            </p>
 */
class MeizhiAdapter (val datas: MutableList<Result>):BaseQuickAdapter<Result,BaseViewHolder>(R.layout.item_meizhi) {
    override fun convert(helper: BaseViewHolder?, item: Result?) {
        helper!!.getView<ImageView>(R.id.iv_meizhi).apply {
            post {
                Timber.e("url  "+item?.url +"datas.size:  "+datas.size)
                if(item?.url!=null&& (item.url.endsWith("jpg")||item.url.endsWith("png")||item.url.endsWith("gif"))){
                    loadImage(YImageConfig.Builder()
                        .url(item.url).imageView(this).bitmapTransformation(BlurTransformation(10))
                        .placeholder(R.drawable.loading_meizhi).build())
                }

            }
        }
    }
}