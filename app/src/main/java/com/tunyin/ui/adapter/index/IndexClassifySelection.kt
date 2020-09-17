package com.tunyin.ui.adapter.index

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder

class IndexClassifySelection(list: List<IndexEntity>?) : StateBroadcast<IndexEntity>(R.layout.layout_item_empty,
        R.layout.item_index_classify_recycleview, list) {

    override fun convert(holder: ViewHolder, classifySelection: IndexEntity, position: Int) {
        with(holder) {
            classifySelection.let {
                var indexClassifyItemAdapter = IndexClassifyItemAdapter()
                indexClassifyItemAdapter.dataList = classifySelection.classifyList
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 4)
                getView<RecyclerView>(R.id.recycler).adapter = indexClassifyItemAdapter

//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        // holder?.setText(R.id.headTitle, "广播剧")?.setVisible(R.id.headTitle, false)?.setVisible(R.id.headMore, false)
    }
}