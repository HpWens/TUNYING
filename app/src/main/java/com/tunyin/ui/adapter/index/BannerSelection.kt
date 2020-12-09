package com.tunyin.ui.adapter.index

import android.content.Intent
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.activity.WebViewActivity
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.BannerViewHolder
import com.tunyin.widget.section.ViewHolder
import com.zhouwei.mzbanner.MZBannerView

class BannerSelection(private val list: ArrayList<IndexEntity.BannerListBean>?) : StateBroadcast<Nothing>(R.layout.layout_banner, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        if (list?.size == 1) {
            list.addAll(list)
            list.addAll(list)
        } else if (list?.size == 2) {
            list.addAll(list)
        }
        val urls = list?.map { it.image }
        val mMZBanner = holder.itemView.findViewById(R.id.banner) as MZBannerView<String>
        mMZBanner.setBannerPageClickListener { view, i ->
            if (list?.get(i)?.type.equals("3")) {
                var intent = Intent(mContext, WebViewActivity::class.java)
                intent.putExtra("load_url", list?.get(i)?.contactId)
                mContext.startActivity(intent)
            } else {
                var id = list?.get(i)?.contactId
                holder.itemView.context.startActivity(id?.let { PlayerActivity.newInstance(holder.itemView.context, it) })
            }
        }
        // 设置数据
        mMZBanner.setPages(urls) {
            BannerViewHolder()
        }

        mMZBanner.start()
    }
}