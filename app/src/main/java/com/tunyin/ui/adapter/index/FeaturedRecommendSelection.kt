package com.tunyin.ui.adapter.index

import android.widget.TextView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.adapter.discovery.StateDiscovery
import com.tunyin.utils.ImageUtil
import com.tunyin.widget.section.ViewHolder

class FeaturedRecommendSelection(list: List<IndexEntity.FeaturedRecommendBean.ListBean>?) : StateDiscovery<IndexEntity.FeaturedRecommendBean.ListBean>(R.layout.layout_item_section_head, R.layout.item_select_recommend, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.FeaturedRecommendBean.ListBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                //                if (position != 0) getView<ConstraintLayout>(R.id.item).setBackgroundResource(R.drawable.ic_bottom_lines)
                ImageUtil.load(it.image).on(getView(R.id.image))

                setText(R.id.tv_title, it.title)
                var id=it.id

                itemView.setOnClickListener {
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context,id))
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }

//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "精选推荐")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshReaturedREcommendListener!=null){
                refreshReaturedREcommendListener.refreshReaturedREcommendData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshReaturedREcommendListener(listener: OnRefreshReaturedREcommendListener) {
        this.refreshReaturedREcommendListener = listener

    }

    private lateinit var refreshReaturedREcommendListener: OnRefreshReaturedREcommendListener


    interface OnRefreshReaturedREcommendListener {
        fun refreshReaturedREcommendData()
    }
}