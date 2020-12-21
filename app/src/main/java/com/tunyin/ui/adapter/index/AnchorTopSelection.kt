package com.tunyin.ui.adapter.index

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunyin.R
import com.tunyin.mvp.model.index.IndexEntity
import com.tunyin.ui.activity.AnchorDetailActivity
import com.tunyin.ui.adapter.discovery.StateBroadcast
import com.tunyin.widget.section.ViewHolder


class AnchorTopSelection(list: List<IndexEntity>?) : StateBroadcast<IndexEntity>(R.layout.layout_item_section_head_for_top, R.layout.item_broadcat_recycleview, list) {

    private var mRecyclerViewLayoutManager: LinearLayoutManager? = null
    override fun convert(holder: ViewHolder, zhuanlan: IndexEntity, position: Int) {
        with(holder) {
            zhuanlan.let {
                mRecyclerViewLayoutManager = LinearLayoutManager(mContext)
                mRecyclerViewLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
                var anchorTopItemAdapter = AnchorTopItemAdapter()
                anchorTopItemAdapter.dataList = zhuanlan.anchorList
                getView<RecyclerView>(R.id.recycler).layoutManager = mRecyclerViewLayoutManager
                getView<RecyclerView>(R.id.recycler).adapter = anchorTopItemAdapter
                anchorTopItemAdapter.setOnItemClickListener { v, position ->
                    mContext.startActivity(AnchorDetailActivity.start(mContext, zhuanlan.anchorList[position].id))
                }
            }
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder?) {
        holder?.setText(R.id.headTitle, "热门主播")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshAnchorTopListener != null) {
                refreshAnchorTopListener.refreshAnchorTopData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshAnchorTopListener(listener: OnRefreshAnchorTopListener) {
        this.refreshAnchorTopListener = listener

    }

    private lateinit var refreshAnchorTopListener: OnRefreshAnchorTopListener


    interface OnRefreshAnchorTopListener {
        fun refreshAnchorTopData()
    }
}