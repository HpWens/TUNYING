package com.tunyin.ui.activity.index

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.meis.base.mei.utils.ParseJsonUtils
import com.tunyin.R
import com.tunyin.base.BaseInjectActivity
import com.tunyin.entity.SearchTypeEntity
import com.tunyin.mvp.contract.index.SearchResultContract
import com.tunyin.mvp.model.index.SearchEntity
import com.tunyin.mvp.presenter.index.SearchResultPresenter
import com.tunyin.ui.adapter.index.SearchResultAdapter
import com.tunyin.utils.StatusBarUtil
import com.vondear.rxtool.RxKeyboardTool
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.SimpleCallBack
import com.zhouyou.http.exception.ApiException
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

    private lateinit var mTabList: List<SearchTypeEntity>

    private var mSelectedPosition = 0

    override fun initInject() = activityComponent.inject(this)

    override fun initPresenter() = mPresenter.attachView(this)


    override fun loadData() {
        mSearchContent = intent.getStringExtra("searchContent")
        if (!TextUtils.isEmpty(mSearchContent)) {
            et_search_content.setText(mSearchContent)
        }

        showLoading()

        // 获取搜索分类
        EasyHttp.post("api/song/searchClass")
                .execute(object : SimpleCallBack<String?>() {
                    override fun onError(e: ApiException) {}
                    override fun onSuccess(t: String?) {
                        mTabList = ParseJsonUtils.parseListData(t, "content", "list", SearchTypeEntity::class.java)
                        if (mTabList.isNotEmpty()) {
                            for (element in mTabList) {
                                tablayout.addTab(tablayout.newTab().setText(element.name))
                            }

                            mSelectedPosition = 0
                            mPresenter.search("0", "20", mSearchContent, mTabList[0].id.toString())
                        }
                    }
                })
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

        layout_empty.visibility = if (searchEntity.list.isEmpty()) View.VISIBLE else View.GONE
    }


    override fun initWidget() {
        StatusBarUtil.setTranslucentForImageView(this, 0, null)

        iv_back.setOnClickListener { finish() }
        tv_cancel.setOnClickListener { finish() }

//        for (i in 0 until mTitles.size) {
//            tablayout.addTab(tablayout.newTab().setText(mTitles[i]))
//            tablayout.addTab(tablayout.newTab().setTag(i))
//        }

        tablayout.addOnTabSelectedListener(this)

        mAdapter = SearchResultAdapter()
        recycler?.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        recycler?.adapter = mAdapter

        mAdapter?.setOnItemClickListener { v, position ->
            startActivity(mContext?.let { PlayerActivity.newInstance(it, mAdapter!!.dataList[position].id) })
        }

        et_search_content.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    var keyword = et_search_content.text.toString().trim()
                    if (!TextUtils.isEmpty(keyword)) {
                        showLoading()
                        mSearchContent = keyword
                        mPresenter.search("0", "20", mSearchContent, mTabList[mSelectedPosition].id.toString())
                    }
                    RxKeyboardTool.hideSoftInput(mContext, et_search_content)
                    return true
                }
                return false
            }

        })
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        showLoading()
        if (p0 != null) {
            mSelectedPosition = p0.position
        }
        mPresenter.search("0", "20", et_search_content.text.trim().toString(), mTabList[mSelectedPosition].id.toString())
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