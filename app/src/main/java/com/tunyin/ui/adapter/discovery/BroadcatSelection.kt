package com.tunyin.ui.adapter.discovery

import android.graphics.Rect
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.tunyin.R
import com.tunyin.mvp.model.discovery.DiscoveryEntity
import com.tunyin.widget.section.ViewHolder
import com.vondear.rxtool.RxImageTool

class BroadcatSelection(list: List<DiscoveryEntity.BroadcastBean>?) : StateBroadcast<DiscoveryEntity.BroadcastBean>(R.layout.layout_item_section_head,
        R.layout.item_broadcat_recycleview, list) {

    lateinit var itemDecoration: ItemDecoration

    override fun convert(holder: ViewHolder, zhuanlan: DiscoveryEntity.BroadcastBean, position: Int) {
        with(holder) {
            zhuanlan.let {
                var broadcastItemAdapter = BroadcastItemAdapter()
                broadcastItemAdapter.dataList = zhuanlan.list
                getView<RecyclerView>(R.id.recycler).layoutManager = GridLayoutManager(mContext, 3)
                getView<RecyclerView>(R.id.recycler).adapter = broadcastItemAdapter
                for (i in 0 until getView<RecyclerView>(R.id.recycler).itemDecorationCount) {
                    getView<RecyclerView>(R.id.recycler).removeItemDecorationAt(i)
                }
                getView<RecyclerView>(R.id.recycler).addItemDecoration(object : ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        var pos = parent.getChildAdapterPosition(view)
                        when {
                            pos % 3 == 0 -> {
                                outRect.left = RxImageTool.dip2px(10f)
                                outRect.right = RxImageTool.dip2px(5f)
                            }
                            pos % 3 == 1 -> {
                                outRect.right = RxImageTool.dip2px(5f)
                                outRect.left = RxImageTool.dip2px(5f)
                            }
                            else -> {
                                outRect.right = RxImageTool.dip2px(10f)
                                outRect.left = RxImageTool.dip2px(5f)
                            }
                        }
                        if (pos / 3 == 0) {
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
        holder?.setText(R.id.headTitle, "广播剧")?.setVisible(R.id.headMore, true)
        holder?.getView<TextView>(R.id.headMore)?.setOnClickListener {
            if (refreshBroadcatListener != null) {
                refreshBroadcatListener.refreshBroadcatData()
            }
//            ToastUtils.showToast("我是换一批")
        }
    }

    fun setRefreshBroadcatListener(listener: OnRefreshBroadcatListener) {
        this.refreshBroadcatListener = listener

    }

    private lateinit var refreshBroadcatListener: OnRefreshBroadcatListener


    interface OnRefreshBroadcatListener {
        fun refreshBroadcatData()
    }
}