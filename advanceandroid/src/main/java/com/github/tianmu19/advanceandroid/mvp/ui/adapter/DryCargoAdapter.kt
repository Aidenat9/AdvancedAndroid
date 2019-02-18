package com.github.tianmu19.advanceandroid.mvp.ui.adapter

import android.text.TextUtils
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.tianmu19.advanceandroid.R
import com.github.tianmu19.advanceandroid.app.loadImage
import com.github.tianmu19.advanceandroid.mvp.model.entity.gank.Result
import com.yangyan.xxp.yangyannew.app.YImageConfig
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author sunwei
 *邮箱：tianmu19@gmail.com
 *时间：2019/2/18 10:57
 *包名：com.github.tianmu19.advanceandroid.mvp.ui.adapter
 *<p>description:            </p>
 */
class DryCargoAdapter(val datas: MutableList<Result>) :
    BaseQuickAdapter<Result, BaseViewHolder>(R.layout.item_gank_android,datas) {
    override fun convert(helper: BaseViewHolder?, item: Result?) {
        helper!!.getView<ImageView>(R.id.iv_android_pic).apply {
            post {
                if(null!=item?.images&&item?.images.size>0){
                    loadImage(
                        YImageConfig.Builder()
                            .bitmapTransformation(RoundedCornersTransformation(10, 0))
                            .imageView(this).url(item.images[0]).placeholder(R.drawable.loading_drawable).build()
                    )
                }
                 }

        }
        helper.setText(R.id.tv_android_des,item!!.desc)
        helper.setText(R.id.tv_android_who,item.who)
        helper.setVisible(R.id.tv_content_type,!TextUtils.isEmpty(item.type))
        helper.setText(R.id.tv_content_type,item.type)
        helper.setText(R.id.tv_android_time,item.publishedAt)

    }
}