package com.tunyin.mvp.contract.index

import com.tunyin.BaseContract
import com.weike.education.mvp.model.app.SelectionBean

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/11/19
 * desc: 精选界面-SelectionContract
 *
 */
interface SelectionContract {

    interface View : BaseContract.BaseView {
        fun showSelection(selectionBean: SelectionBean)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getSelection()
    }
}