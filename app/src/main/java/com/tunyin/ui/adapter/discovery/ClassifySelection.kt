package com.tunyin.ui.adapter.discovery

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.widget.section.ViewHolder

class ClassifySelection(list: List<DiscoveryEntity>?) : StateBroadcast<DiscoveryEntity>(R.layout.layout_item_empty,
        R.layout.item_classify_recycleview, list) {

    override fun convert(holder: ViewHolder, classifySelection: DiscoveryEntity, position: Int) {
        with(holder) {
            classifySelection.let {
                var classifyItemAdapter = ClassifyItemAdapter()
                classifyItemAdapter.dataList = classifySelection.classifyList
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 4)
                getView<RecyclerView>(R.id.recycler).adapter = classifyItemAdapter

//                itemView.setOnClickListener {
//                    //                    mContext.startActivity(Intent(mContext, WebViewActivity::class.java))
//                }


            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        //holder?.setText(R.id.headTitle, "广播剧")?.setVisible(R.id.headTitle, false)?.setVisible(R.id.headMore, false)
    }
}