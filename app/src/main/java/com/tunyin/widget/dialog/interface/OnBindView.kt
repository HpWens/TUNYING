package com.ccb.cdialog

import android.view.View
import inno.tmsclient.widget.dialog.BaseDialog

interface OnBindView{
        fun onBind(dialog: BaseDialog?, view : View)
    }