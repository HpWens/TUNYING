package com.ccb.cdialog

import android.view.View
import inno.tmsclient.widget.dialog.BaseDialog

interface OnButtonClick{
        fun onClickLift(dialog: BaseDialog, v : View)
        fun onClickCenter(dialog: BaseDialog ,v : View)
        fun onClickRight(dialog: BaseDialog ,v : View)
    }
