package com.tunyin.ui.adapter.index

import android.widget.TextView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.adapter.discovery.StateDiscovery
import com.tunyin.utils.ImageUtil
import com.tunyin.widget.section.ViewHolder

class SleepTopSelection(list: List<IndexEntity.SleepTopBean.ListBeanXXXX>?) :
        StateDiscovery<IndexEntity.SleepTopBean.ListBeanXXXX>(R.layout.layout_item_section_head,
                R.layout.item_best_to_sleep_top, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.SleepTopBean.ListBeanXXXX, position: Int) {
        with(holder) {
            zhuanlan.let {
                //                if (position != 0) getView<ConstraintLayout>(R.id.item).setBackgroundResource(R.drawable.ic_bottom_lines)

                setText(R.id.tv_title, it.title)
                setText(R.id.tv_sub_title, it.content)
                setText(R.id.tv_diamond_num, it.price)

                ImageUtil.load(it.image).on(getView(R.id.image))

                var id=it.id

                itemView.setOnClickListener {
                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context,id))
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
                }

//                itemView.setOnClickListener {
//                    itemView.context.startActivity(PlayerActivity.newInstance(itemView.context, it.id.toString()))
//                    var intent=Intent(PlayerActivity.newInstance(itemView.context,it.id.toString()))
//                    itemView.context.startActivity(intent)
                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "最佳哄睡榜")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshSleepTopListener != null) {
                refreshSleepTopListener.refreshSleepTopData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshSleepTopListener(listener: OnRefreshSleepTopListener) {
        this.refreshSleepTopListener = listener

    }

    private lateinit var refreshSleepTopListener: OnRefreshSleepTopListener


    interface OnRefreshSleepTopListener {
        fun refreshSleepTopData()
    }
}