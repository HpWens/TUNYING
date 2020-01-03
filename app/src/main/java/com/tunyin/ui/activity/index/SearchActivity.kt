package com.tunyin.ui.activity.index

import android.util.TypedValue
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.library.flowlayout.FlowLayoutManager
import com.library.flowlayout.SpaceItemDecoration
import com.tunyin.ToastUtils
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.app.MainContract
import com.tunyin.mvp.contract.index.SearchContract
import com.tunyin.mvp.model.index.SearchHistoryEntity
import com.tunyin.mvp.model.index.SearchHotEntity
import com.tunyin.mvp.presenter.app.MainPresenter
import com.tunyin.mvp.presenter.index.SearchHotPresenter
import com.tunyin.ui.adapter.index.PlayerDirectoryAdapter
import com.tunyin.ui.adapter.index.SearchHistoryAdapter
import com.tunyin.ui.adapter.index.SearchHotAdapter
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView


/**
 * 搜索页面
 */
class SearchActivity : BaseInjectActivity<SearchHotPresenter>(), SearchContract.View, View.OnClickListener {


    private var mAdapter: SearchHotAdapter? = null
    override fun getLayoutId(): Int = com.tunyin.R.layout.activity_search
    private val hotList = ArrayList<SearchHotEntity.ListBean>()
    private val historyList = ArrayList<SearchHistoryEntity.ListBean>()
    private var removeHistoryPos: Int = -1

    private var mAdapterHistory: SearchHistoryAdapter? = null


    override fun showError(msg: String) {
        hideLoading()
    }

    override fun showSearchHistoryData(searchHistoryEntity: SearchHistoryEntity) {
//        if (searchHistoryEntity.list.size == 0) {
//            tv_clear_all.visibility = View.GONE
//        } else {
//            tv_clear_all.visibility = View.VISIBLE
//        }
        hideLoading()
        historyList.clear()
        historyList.addAll(searchHistoryEntity.list)
        mAdapterHistory?.dataList = historyList
        mAdapterHistory?.notifyDataSetChanged()
    }

    override fun showSearchHotData(searchHotEntity: SearchHotEntity) {
        hideLoading()
        et_search_content.hint = searchHotEntity.hotSearch
        hotList.clear()
        hotList.addAll(searchHotEntity.list)
        mAdapter?.dataList = hotList
        mAdapter?.notifyDataSetChanged()
    }

    override fun deleteSearchSuc(suc: String) {
        hideLoading()
        ToastUtils.showToast(suc)
//        mAdapterHistory?.notifyItemRemoved(removeHistoryPos)
        mAdapterHistory!!.dataList.remove(mAdapterHistory!!.dataList[removeHistoryPos])
        mAdapterHistory!!.notifyDataSetChanged()
    }

    override fun deleteSearchAllSuc(suc: String) {
        hideLoading()
        mAdapterHistory!!.dataList.clear()
        mAdapterHistory!!.notifyDataSetChanged()
    }

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)

    override fun onResume() {
        super.onResume()
        showLoading()
        mPresenter.getSearchHot()
        mPresenter.getSearchHistory()
    }

    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        tv_cancel.setOnClickListener(this)
        tv_clear_all.setOnClickListener(this)
        search_layout.setOnClickListener(this)
        et_search_content.setOnClickListener(this)
        et_search_content.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                startActivity(SearchResultActivity.newInstance(mContext, et_search_content.text.trim().toString()))
                true
            } else false
        }
//        mPresenter.getSearchHot()
//        mPresenter.getSearchHistory()


        val flowLayoutManager = FlowLayoutManager()
        recycler.addItemDecoration(SpaceItemDecoration(dp2px(4f)))
        recycler.layoutManager = flowLayoutManager
        mAdapter = SearchHotAdapter()
        recycler.adapter = mAdapter

        mAdapterHistory = SearchHistoryAdapter()
        recycler_history?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler_history?.adapter = mAdapterHistory

        mAdapterHistory!!.listener = object : SearchHistoryAdapter.DeleteSearchListener {
            override fun onDeleteSearchClick(position: Int) {
                showLoading()
                mPresenter.deleteSearch(mAdapterHistory!!.dataList[position].id)
                removeHistoryPos = position
            }
        }

        mAdapter!!.setOnItemClickListener { v, position ->
            startActivity(SearchResultActivity.newInstance(mContext, mAdapter!!.dataList[position].title))

        }

        mAdapterHistory!!.setOnItemClickListener { v, position ->
            startActivity(SearchResultActivity.newInstance(mContext, mAdapterHistory!!.dataList[position].title))

        }


    }

    override fun onClick(p0: View?) {
        when (p0) {
            tv_cancel -> {
                finish()
            }
            tv_clear_all -> {
                showLoading()
                mPresenter.deleteSearch("0")
            }
            search_layout -> {
                startActivity(SearchResultActivity.newInstance(mContext, ""))

            }

        }
    }

    private fun dp2px(value: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
    }
}