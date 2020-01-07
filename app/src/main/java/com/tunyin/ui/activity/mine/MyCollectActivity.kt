package com.tunyin.ui.activity.mine

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunyin.R
import com.tunyin.base.BaseActivity
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.mine.CollectContract
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.model.mine.CollectEntity
import com.tunyin.mvp.presenter.mine.CollectPresenter
import com.tunyin.ui.adapter.index.SearchResultAdapter
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



    override fun getLayoutId(): Int {
        return R.layout.activity_my_collect
    }

    override fun initWidget() {
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        tv_title.text = "我的收藏"


        mAdapter = CollectAdapter()
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