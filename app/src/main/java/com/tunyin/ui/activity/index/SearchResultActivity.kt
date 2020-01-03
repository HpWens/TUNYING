package com.tunyin.ui.activity.index

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tunyin.R
import com.tunyin.base.BaseInjectActivity
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.presenter.index.SearchResultPresenter
import com.tunyin.ui.adapter.index.SearchResultAdapter
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search_result.*


/**
 * create by wangrongchao
 * on 2019/10/31
 *搜索结果
 **/
class SearchResultActivity : BaseInjectActivity<SearchResultPresenter>(), TabLayout.OnTabSelectedListener, SearchResultContract.View, View.OnClickListener {


    override fun getLayoutId(): Int = R.layout.activity_search_result
    private var mSearchContent: String = ""
    private val mTitles = arrayOf("推荐", "付费精品", "广播剧", "主播哄睡", "电台", "自然声")

    private var mAdapter: SearchResultAdapter? = null
    private val searchList = ArrayList<SearchEntity.ListBean>()

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun loadData() {
        mSearchContent = intent.getStringExtra("searchContent")
        if (!TextUtils.isEmpty(mSearchContent)) {
            et_search_content.setText(mSearchContent)
        }

        showLoading()
        mPresenter.search("0", "10", mSearchContent, "")


    }

    override fun showError(msg: String) {
        hideLoading()
    }

    override fun showSearchData(searchEntity: SearchEntity) {
        hideLoading()
        searchList.clear()
        searchList.addAll(searchEntity.list)
        mAdapter?.dataList = searchList
        mAdapter?.notifyDataSetChanged()
    }


    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)
        for (i in 0 until mTitles.size) {
            tablayout.addTab(tablayout.newTab().setText(mTitles[i]))
//            tablayout.addTab(tablayout.newTab().setTag(i))
        }
        mAdapter = SearchResultAdapter()
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter

        mAdapter?.setOnItemClickListener { v, position ->
            startActivity(mContext?.let { PlayerActivity.newInstance(it, mAdapter!!.dataList[position].id) })
        }
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
        showLoading()
        mPresenter.search("0", "10", et_search_content.text.trim().toString(), "1")
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {


    }


    override fun onClick(p0: View?) {
        when (p0) {

        }
    }


    companion object {

        @JvmStatic
        fun newInstance(context: Context?, searchContent: String): Intent {
            val intent = Intent(context, SearchResultActivity::class.java)
            intent.putExtra("searchContent", searchContent)
            return intent
        }
    }
}