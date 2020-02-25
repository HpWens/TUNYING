package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.model.mine.CollectEntity
import com.tunyin.mvp.presenter.mine.CollectPresenter
import com.tunyin.ui.adapter.mine.CollectAdapter
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * 我的收藏
 */
class MyCollectActivity : BaseInjectActivity<CollectPresenter>(), CollectContract.View {

    private var mAdapter: CollectAdapter? = null
    private val collectList = ArrayList<CollectEntity.ListBean>()

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun showCollectData(collectEntity: CollectEntity) {
        hideLoading()
        collectList.addAll(collectEntity.list)
        mAdapter?.dataList = collectList
        mAdapter?.notifyDataSetChanged()
    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun showCancelCollectSuccess(pos: Int) {
        ToastUtils.showToast("取消收藏成功")
        if (mAdapter != null && mAdapter!!.dataList.size > pos) {
            mAdapter!!.dataList[pos].isHelperCollect = false
            mAdapter!!.notifyItemChanged(pos)
//            mAdapter!!.notifyItemRemoved(pos)
//            mAdapter!!.dataList.removeAt(pos)
        }
    }

    override fun showAddCollectSuccess(pos: Int) {
        ToastUtils.showToast("收藏成功")
        if (mAdapter != null && mAdapter!!.dataList.size > pos) {
            mAdapter!!.dataList[pos].isHelperCollect = true
            mAdapter!!.notifyItemChanged(pos)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_collect
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "我的收藏"

        mAdapter = CollectAdapter(object : CollectAdapter.OnCollectListener {
            override fun onAddCollect(pos: Int, songId: String) {
                mPresenter.addCollect(pos, songId)
            }

            override fun onCancelCollect(pos: Int, songId: String) {
                mPresenter.cancelCollect(pos, songId)
            }
        })
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter

        showLoading()
        mPresenter.getCollect("0", "20")
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context?): Intent {
            val intent = Intent(context, MyCollectActivity::class.java)
            return intent
        }
    }
}