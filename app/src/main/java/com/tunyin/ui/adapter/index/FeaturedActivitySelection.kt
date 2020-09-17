package com.tunyin.ui.adapter.index

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder
import com.vondear.rxtool.RxImageTool

class FeaturedActivitySelection(list: List<IndexEntity.FeaturedActivityBean>?) : StateBroadcast<IndexEntity.FeaturedActivityBean>(R.layout.layout_item_section_head,
        R.layout.item_index_guess_like, list) {

    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity.FeaturedActivityBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                var featuredActivityItemAdapter = FeaturedActivityItemAdapter()
                featuredActivityItemAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 2)
                getView<RecyclerView>(R.id.recycler).adapter = featuredActivityItemAdapter
                for (i in 0 until getView<RecyclerView>(R.id.recycler).itemDecorationCount) {
                    getView<RecyclerView>(R.id.recycler).removeItemDecorationAt(i)
                }
                getView<RecyclerView>(R.id.recycler).addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        var pos = parent.getChildAdapterPosition(view)
                        if (pos / 2 == 0) {
                            outRect.top = 0
                        } else {
                            outRect.top = RxImageTool.dip2px(20f)
                        }
                    }
                })

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