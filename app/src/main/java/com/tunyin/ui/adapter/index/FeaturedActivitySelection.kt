package com.tunyin.ui.adapter.index

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder

class FeaturedActivitySelection(list: List<IndexEntity.FeaturedActivityBean>?) : StateBroadcast<IndexEntity.FeaturedActivityBean>(R.layout.layout_item_section_head,
        R.layout.item_index_guess_like, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.FeaturedActivityBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                var featuredActivityItemAdapter = FeaturedActivityItemAdapter()
                featuredActivityItemAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 2)
                getView<RecyclerView>(R.id.recycler).adapter = featuredActivityItemAdapter


//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "精选活动")?.setVisible(R.id.headMore, false)
    }
}