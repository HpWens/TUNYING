package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import android.view.View
import com.tunyin.MyAudioPlayer
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.OrderContract
import com.tunyin.mvp.model.mine.OrderEntity
import com.tunyin.mvp.presenter.mine.OrderPresenter
import com.tunyin.ui.activity.index.PlayerActivity
import com.tunyin.utils.ImageUtil
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * create by wangrongchao
 * on 2019/11/9
 *
 **/
class OrderDetailActivity : BaseInjectActivity<OrderPresenter>(), OrderContract.View, View.OnClickListener {


    private var id: String? = null
    private var songId: String? = null
    private var title: String? = null
    private var imageUrl: String? = null
    private var orderNo: String? = null
    private var orderDate: String? = null
    private var price: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_order_detail
    }

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun showOrderList(orderEntity: OrderEntity) {
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun delOrderSuc(string: String) {
        hideLoading()
        ToastUtils.showToast(string)
        finish()
    }

    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        tv_title.text = "订单详情"
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        imageUrl = intent.getStringExtra("imageUrl")
        orderNo = intent.getStringExtra("orderNo")
        orderDate = intent.getStringExtra("orderDate")
        price = intent.getStringExtra("price")
        songId = intent.getStringExtra("songId")
        tv_delete.setOnClickListener(this)
        image.setOnClickListener(this)
        ImageUtil.load(imageUrl).on(image)
        tv_title_content.text = title
        tv_order_date.text = orderDate
        tv_order_no.text = orderNo
        tv_order_price.text = price
    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_delete -> {
                id?.let {
                    showLoading()
                    mPresenter.delOrder(it)
                }
            }

            image -> {

                startActivity(PlayerActivity.newInstance(mContext!!, songId!!))

//                val intent = mContext?.let { id?.let { it1 -> PlayerActivity.newInstance(it, it1) } }
//                startActivity(intent)
            }
        }
    }


    override fun initToolbar() {
        super.initToolbar()
    }

    companion object {
        fun newInstance(context: Context?,
                        id: String,
                        title: String?,
                        imageUrl: String,
                        orderNo: String,
                        orderDate: String,
                        price: String,
                        songId: String): Intent {
            val intent = Intent(context, OrderDetailActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("title", title)
            intent.putExtra("imageUrl", imageUrl)
            intent.putExtra("orderNo", orderNo)
            intent.putExtra("orderDate", orderDate)
            intent.putExtra("price", price)
            intent.putExtra("songId", songId)
            return intent
        }
    }

    override fun onResume() {
        super.onResume()
        var isPlaying = MyAudioPlayer.get().isPlaying && MyAudioPlayer.get().songId == songId
        tv_play_status.text = if (isPlaying) "正在播放" else "未开始"
    }

}