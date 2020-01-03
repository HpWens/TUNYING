package com.weike.education.ui.adapter.app.section

import android.content.Intent
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tunyin.R
import com.tunyin.widget.section.StatelessSection
import com.tunyin.widget.section.ViewHolder
import com.weike.education.mvp.model.app.SelectionBean
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/21
 * desc: 首页-名师推荐-section
 *
 */
class SectionSpecial(list: List<SelectionBean.Data.Zhuanlan>?) : StatelessSection<SelectionBean.Data.Zhuanlan>(R.layout.layout_item_section_head, R.layout.layout_item_special_body, list) {

    override fun convert(holder: ViewHolder, zhuanlan: SelectionBean.Data.Zhuanlan, position: Int) {
        with(holder) {
            zhuanlan.let {
                if (position != 0) getView<ConstraintLayout>(R.id.item).setBackgroundResource(R.drawable.ic_bottom_lines)

                setText(R.id.special_title, it.title)
                setText(R.id.special_teacherName, it.teacherName)
                setText(R.id.special_teacherTag, it.teacherTag)
                setText(R.id.special_content, it.introduction)

//                Glide.with(mContext)
//                        .load(it.img)
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .bitmapTransform(RoundedCornersTransformation(mContext, 5, 0))
//                        .into(getView(R.id.special_imgView))

                itemView.setOnClickListener {
//                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "名师推荐")?.setVisible(R.id.headMore, false)
    }
}