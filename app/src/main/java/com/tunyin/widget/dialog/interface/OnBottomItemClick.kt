package com.ccb.cdialog

import android.view.View
import inno.tmsclient.widget.dialog.BaseDialog

interface OnBottomItemClick{
        fun onItemClick(dialog: BaseDialog, position : Int)
    }
