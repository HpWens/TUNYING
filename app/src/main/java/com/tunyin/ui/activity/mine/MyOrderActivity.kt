package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseActivity
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.OrderContract
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.mvp.presenter.mine.OrderPresenter
import com.tunyin.ui.activity.index.PayStuffActivity
import com.tunyin.ui.adapter.mine.OrderAdapter
import com.tunyin.ui.adapter.mine.OrderForActivityAdapter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 我的订单
 */
class MyOrderActivity : BaseInjectActivity<OrderPresenter>(), OrderContract.View {


    private var mAdapter: OrderForActivityAdapter? = null
    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun showOrderList(orderEntity: OrderEntity) {
        mAdapter = OrderForActivityAdapter()
        mAdapter?.dataList = orderEntity.list
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { v, position ->
            startActivity(OrderDetailActivity.newInstance(mContext, mAdapter!!.dataList[position].id,
                    mAdapter!!.dataList[position].title,
                    mAdapter!!.dataList[position].image,
                    mAdapter!!.dataList[position].orderNo,
                    mAdapter!!.dataList[position].createDateFormat,
                    mAdapter!!.dataList[position].totalPrice,
                    mAdapter!!.dataList[position].songId
            ))
        }
        mAdapter!!.setOnDeleteClickListener(object : OrderForActivityAdapter.OnDeleteClickLister {
            override fun onDeleteClick(position: Int) {
                mPresenter.delOrder(mAdapter!!.dataList[position].id)
                mAdapter!!.dataList.removeAt(position)

            }

        })
    }

    override fun delOrderSuc(string: String) {
        ToastUtils.showToast(string)
        this.mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_order
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "我的订单"
        mPresenter.getOrderList("0", "20")
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, MyOrderActivity::class.java)
            return intent
        }
    }
}